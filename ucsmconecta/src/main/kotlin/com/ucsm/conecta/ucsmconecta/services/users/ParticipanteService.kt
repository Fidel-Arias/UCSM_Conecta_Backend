package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.auth.participante.RegisterParticipanteData
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.ParticipanteBusquedaDTO
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.repository.users.participante.ParticipanteRepository
import jakarta.persistence.EntityNotFoundException
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
    fun createParticipante(@RequestBody @Valid registerParticipanteData: RegisterParticipanteData): Participante {
        // Buscar entidades relacionadas
        val tipoParticipante: TipoParticipante? = tipoParticipanteService.searchById(registerParticipanteData.tipoParticipanteId)

        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.searchById(registerParticipanteData.escuelaProfesionalId)

        val congreso: Congreso? = congresoService.searchById(registerParticipanteData.congresoId)

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

    fun searchByNumDocumento(numDocumento: String): Participante? = participanteRepository.findByNumDocumento(numDocumento)
        .orElseThrow { RuntimeException("Participante no encontrado por su numero de documento") }

    fun searchByNombres(nombres: String): List<Participante> = participanteRepository.findByNombres(nombres)

    fun searchByApellidos(apPaterno: String, apMaterno: String): List<ParticipanteBusquedaDTO> {
        val apellidosCompletos: String = "$apPaterno $apMaterno"
        val resultados = participanteRepository.findByApellidos(apellidosCompletos)

        if (resultados.isEmpty()) {
            throw EntityNotFoundException("No se encontraron participantes con los apellidos proporcionados")
        }
        return resultados
    }

    fun getAllParticipantes(): List<Participante> = participanteRepository.findAll()

    fun getParticipanteById(id: Long): Participante? = participanteRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Participante no encontrado") }
}