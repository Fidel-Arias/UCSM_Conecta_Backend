package com.ucsm.conecta.ucsmconecta.services.create.users

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataRequestParticipante
import com.ucsm.conecta.ucsmconecta.services.create.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.create.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.repository.users.participante.ParticipanteRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

class ParticipanteService @Autowired constructor(
    private val participanteRepository: ParticipanteRepository,
    private val tipoParticipanteService: TipoParticipanteService,
    private val escuelaProfesionalService: EscuelaProfesionalService,
    private val congresoService: CongresoService
){
    fun createParticipante(@RequestBody @Valid dataRequestParticipante: DataRequestParticipante): Participante {
        // Buscar entidades relacionadas
        val tipoParticipante: TipoParticipante = tipoParticipanteService.searchById(dataRequestParticipante.tipoParticipanteId)

        val escuelaProfesional = escuelaProfesionalService.searchById(dataRequestParticipante.escuelaProfesionalId)

        val congreso = congresoService.searchById(dataRequestParticipante.congresoId)

        // Crear y guardar el participante
        return participanteRepository.save(Participante(
            nombres = dataRequestParticipante.nombres,
            apPaterno = dataRequestParticipante.apPaterno,
            apMaterno = dataRequestParticipante.apMaterno,
            numDocumento = dataRequestParticipante.numDocumento,
            email = dataRequestParticipante.email,
            tipoParticipante = tipoParticipante,
            escuelaProfesional = escuelaProfesional,
            congreso = congreso,
            estado = dataRequestParticipante.estado,
            qr_code = dataRequestParticipante.qr_code
        ))
    }

    fun searchByNumDocumento(numDocumento: String): Participante = participanteRepository.findByNumDocumento(numDocumento)
        .orElseThrow { RuntimeException("Participante no encontrado por su numero de documento") }

    fun searchByNombres(nombres: String): List<Participante> = participanteRepository.findByName(nombres)

    fun searchByApellidos(apPaterno: String, apMaterno: String) = participanteRepository.findByApellidos(apMaterno, apMaterno)
        .orElseThrow { RuntimeException("Participante no encontrado por apellidos") }

    fun getAllParticipantes(): List<Participante> = participanteRepository.findAll()

    fun getParticipanteById(id: Long): Participante = participanteRepository.findById(id)
        .orElseThrow { RuntimeException("Participante no encontrado") }
}