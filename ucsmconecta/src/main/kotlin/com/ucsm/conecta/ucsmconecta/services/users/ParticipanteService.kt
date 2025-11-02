package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.auth.participante.RegisterParticipanteData
import com.ucsm.conecta.ucsmconecta.dto.users.auth.participante.UpdateDataParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.ParticipanteBusquedaDTO
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.repository.users.participante.ParticipanteRepository
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ParticipanteService @Autowired constructor(
    private val participanteRepository: ParticipanteRepository,
    private val tipoParticipanteService: TipoParticipanteService,
    private val escuelaProfesionalService: EscuelaProfesionalService,
    private val congresoService: CongresoService
){
    // Metodo para crear un nuevo participante
    @Transactional
    fun createParticipante(@RequestBody @Valid registerParticipanteData: RegisterParticipanteData): Participante {
        // Buscar entidades relacionadas
        val tipoParticipante: TipoParticipante = tipoParticipanteService.searchById(registerParticipanteData.tipoParticipanteId)

        val escuelaProfesional: EscuelaProfesional = escuelaProfesionalService.searchEscuelaProfesionalById(registerParticipanteData.escuelaProfesionalId)

        val congreso: Congreso = congresoService.getCongresoById(registerParticipanteData.congresoId)

        // Crear y guardar el participante
        return participanteRepository.save(Participante(
            nombres = registerParticipanteData.nombres,
            apPaterno = registerParticipanteData.apPaterno,
            apMaterno = registerParticipanteData.apMaterno,
            numDocumento = registerParticipanteData.numDocumento,
            email = registerParticipanteData.email,
            tipoParticipante = tipoParticipante,
            escuelaProfesional = escuelaProfesional,
            congreso = congreso,
            estado = registerParticipanteData.estado,
            qr_code = registerParticipanteData.qr_code
        ))
    }

    // Metodo para buscar participante por su numero de documento
    fun searchByNumDocumento(numDocumento: String): Participante = participanteRepository.findByNumDocumento(numDocumento)
        .orElseThrow { ResourceNotFoundException("Participante no encontrado por su numero de documento $numDocumento") }

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
        .orElseThrow { ResourceNotFoundException("Participante con id $id no encontrado") }

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