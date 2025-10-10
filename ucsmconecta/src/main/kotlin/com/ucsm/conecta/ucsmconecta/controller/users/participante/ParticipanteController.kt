package com.ucsm.conecta.ucsmconecta.controller.users.participante

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.dto.users.auth.participante.RegisterParticipanteData
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResponseParticipante
import com.ucsm.conecta.ucsmconecta.services.users.ParticipanteService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/participantes")
class ParticipanteController @Autowired constructor(
    private val participanteService: ParticipanteService
) {

    @PostMapping
    @Transactional
    fun createParticipante(@RequestBody @Valid registerParticipanteData: RegisterParticipanteData, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<Participante> {
        // Crear el participante
        val participante = participanteService.createParticipante(registerParticipanteData)

        // Se pasan los datos creados a DataResponseParticipante para visualizarlos
        val dataResponseParticipante = DataResponseParticipante(
            id = participante.id!!,
            nombres = participante.nombres,
            apPaterno = participante.apPaterno,
            apMaterno = participante.apMaterno,
            estado = participante.estado,
            nDocumento = participante.numDocumento,
            escuelaProfesionalId = participante.escuelaProfesional.id,
            tipoParticipanteId = participante.tipoParticipante.id,
            qrCode = participante.qr_code
        )

        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/participantes/{id}")
            .buildAndExpand(participante.id)
            .toUri()
        // Retornar la respuesta con el código de estado 201 Created y la ubicación del nuevo recurso
        return ResponseEntity.created(uri).body(participante)
    }
}