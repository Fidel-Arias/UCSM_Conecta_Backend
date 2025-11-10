package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.register.participante.UpdateDataParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.ParticipanteBusquedaDTO
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.repository.users.participante.ParticipanteRepository
import com.ucsm.conecta.ucsmconecta.util.ExcelReader
import com.ucsm.conecta.ucsmconecta.util.QRCodeGenerator
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.multipart.MultipartFile

@Service
class ParticipanteService @Autowired constructor(
    private val participanteRepository: ParticipanteRepository,
    private val congresoAdministradorService: CongresoAdministradorService,
    private val tipoParticipanteService: TipoParticipanteService,
    private val escuelaProfesionalService: EscuelaProfesionalService,
    private val congresoService: CongresoService
){
    // Metodo para crear un nuevo participante
    @Transactional
    fun registrarParticipantesDesdeExcel(file: MultipartFile, adminId: Long): Map<String, Any> {
        val participantesExcel = ExcelReader.leerParticipantesDesdeExcel(file)

        // Buscar al admin para obtener su escuela y congreso
        val adminCongreso = congresoAdministradorService.getAdministradorWithCongresoById(adminId)

        // Cachear entidades comunes (no repetir consultas)
        val escuelaProfesional = escuelaProfesionalService.searchByCodigo(adminCongreso.administrador.escuelaProfesional.codigo)
        val congreso = congresoService.searchByCodigo(adminCongreso.congreso.codigo)

        // Lista mutable de participantes registrados
        val existentes = participanteRepository.findAllNumDocumentos()
        val duplicados = mutableListOf<String>()
        val nuevos = mutableListOf<Participante>()

        for (p in participantesExcel) {
            if (p.numDocumento in existentes) {
                duplicados.add(p.numDocumento)
                continue
            }

            // Buscar y verificar que exista el Tipo Participante
            val tipoParticipante: TipoParticipante = tipoParticipanteService.searchByDescripcion(p.tipoParticipante)

            // ✅ Generar QR para este participante
            val qrPath = QRCodeGenerator.generarQR(
                nombres = "${p.nombres} ${p.apPaterno} ${p.apMaterno}",
                numDocumento = p.numDocumento,
            )

            nuevos.add(
                Participante(
                    nombres = p.nombres,
                    apPaterno = p.apPaterno,
                    apMaterno = p.apMaterno,
                    numDocumento = p.numDocumento,
                    email = p.email,
                    tipoParticipante = tipoParticipante,
                    escuelaProfesional = escuelaProfesional,
                    congreso = congreso,
                    estado = verificarEstado(p.estado),
                    qr_code = qrPath
                )
            )
        }

        // Inserción masiva (1 solo viaje a la base)
        participanteRepository.saveAll(nuevos)

        return mapOf(
            "totalLeidos" to participantesExcel.size,
            "registrados" to nuevos.size,
            "duplicados" to duplicados.size
        )
    }

    // Metodo para validar si se agrega o no a la base de datos segun su estado
    fun verificarEstado(estado: String): String {
        return if (estado == "3PG") "MATRICULADO" else "EN PROCESO"
    }

    // Metodo para buscar participante por su numero de documento
    fun searchByNumDocumento(numDocumento: String): Participante = participanteRepository.findByNumDocumento(numDocumento)
        .orElseThrow { ResourceNotFoundException("Nº de documento $numDocumento desconocido") }

    // Metodo para buscar participantes por nombres
    fun searchByNombres(nombres: String): List<ParticipanteBusquedaDTO> = participanteRepository.findByNombres(nombres)

    // Metodo para buscar participantes por apellidos
    fun searchByApellidos(apPaterno: String?, apMaterno: String?): List<ParticipanteBusquedaDTO> {
        val busqueda = when {
            !apPaterno.isNullOrBlank() && !apMaterno.isNullOrBlank() -> "$apPaterno $apMaterno"
            !apPaterno.isNullOrBlank() -> apPaterno
            !apMaterno.isNullOrBlank() -> apMaterno
            else -> return emptyList()
        }
        val resultados = participanteRepository.findByApellidos(busqueda)
        return resultados
    }

    // Metodo para obtener todos los participantes
    fun getAllParticipantes(): List<Participante> = participanteRepository.findAll()

    // Metodo para obtener un participante por su ID
    fun getParticipanteById(id: Long): Participante = participanteRepository.findById(id)
        .orElseThrow { ResourceNotFoundException("Participante no encontrado") }

    // Metodo para actualizar el estado de un participante por su ID
    @Transactional
    fun updateParticipanteEstadoById(id: Long, nuevoEstado: String): Participante {
        val participante: Participante = getParticipanteById(id)
        participante.estado = nuevoEstado
        return participanteRepository.save(participante)
    }

    // Metodo para cambiar el estado del participante por ID
    @Transactional
    fun changeStateParticipante(id: Long, @RequestBody @Valid updateDataParticipante: UpdateDataParticipante): Participante {
        val participante: Participante = getParticipanteById(id)

        // Solo actualiza si los campos no son nulos o vacíos
        updateDataParticipante.estado.takeIf { !it.isNullOrBlank() }?.let {
            participante.estado = it
        }

        return participanteRepository.save(participante)
    }
}