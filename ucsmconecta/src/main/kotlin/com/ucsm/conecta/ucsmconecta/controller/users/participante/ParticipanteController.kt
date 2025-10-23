package com.ucsm.conecta.ucsmconecta.controller.users.participante

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.auth.participante.RegisterParticipanteData
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResponseParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.ParticipanteBusquedaDTO
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.users.ParticipanteService
import com.ucsm.conecta.ucsmconecta.services.users.TipoParticipanteService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/participantes")
class ParticipanteController @Autowired constructor(
    private val participanteService: ParticipanteService,
    private val escuelaProfesionalService: EscuelaProfesionalService,
    private val tipoParticipanteService: TipoParticipanteService
    ) {

    @PostMapping
    @Transactional
    fun createParticipante(
        @RequestBody @Valid registerParticipanteData: RegisterParticipanteData,
        uriComponentsBuilder: ServletUriComponentsBuilder
    ): ResponseEntity<DataResponseParticipante> {
        // Crear el participante
        val participante: Participante? = participanteService.createParticipante(registerParticipanteData)

        // Buscar la escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional? =
            escuelaProfesionalService.getEscuelaProfesionalById(registerParticipanteData.escuelaProfesionalId)

        // Buscar el tipo de participante asociado
        val tipoParticipante = tipoParticipanteService.searchById(registerParticipanteData.tipoParticipanteId)

        // Buscar el congreso asociado
        val congreso = registerParticipanteData.congresoId

        // Se pasan los datos creados a DataResponseParticipante para visualizarlos
        val dataResponseParticipante = DataResponseParticipante(
            id = participante?.id!!,
            nombres = participante.nombres,
            apPaterno = participante.apPaterno,
            apMaterno = participante.apMaterno,
            estado = participante.estado,
            nDocumento = participante.numDocumento,
            escuelaProfesionalId = escuelaProfesional?.id,
            tipoParticipanteId = tipoParticipante?.id,
            congresoId = congreso,
            qrCode = participante.qr_code
        )

        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/participantes/{id}")
            .buildAndExpand(participante.id)
            .toUri()
        // Retornar la respuesta con el código de estado 201 Created y la ubicación del nuevo recurso
        return ResponseEntity.created(uri).body(dataResponseParticipante)
    }

    @GetMapping("/{id}")
    fun getParticipanteById(@PathVariable id: Long): ResponseEntity<DataResponseParticipante> {
        // Buscar el participante por ID
        val participante = participanteService.getParticipanteById(id)

        // Buscar la escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional? =
            escuelaProfesionalService.getEscuelaProfesionalById(participante?.escuelaProfesional?.id!!)

        // Buscar el tipo de participante asociado
        val tipoParticipante: TipoParticipante? =
            tipoParticipanteService.searchById(participante.tipoParticipante?.id!!)

        // Buscar el congreso asociado
        val congreso = participante?.congreso?.id

        val dataResponseParticipante = DataResponseParticipante(
            id = participante?.id!!,
            nombres = participante.nombres,
            apPaterno = participante.apPaterno,
            apMaterno = participante.apMaterno,
            estado = participante.estado,
            nDocumento = participante.numDocumento,
            escuelaProfesionalId = escuelaProfesional?.id,
            tipoParticipanteId = tipoParticipante?.id,
            congresoId = congreso,
            qrCode = participante.qr_code
        )

        return ResponseEntity.ok(dataResponseParticipante)
    }

    @GetMapping
    fun getAllParticipantes(): ResponseEntity<List<DataResponseParticipante>> {
        val participantes = participanteService.getAllParticipantes()

        if (participantes.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        val dataResponseParticipantes = participantes.map { participante ->
            DataResponseParticipante(
                id = participante.id!!,
                nombres = participante.nombres,
                apPaterno = participante.apPaterno,
                apMaterno = participante.apMaterno,
                estado = participante.estado,
                nDocumento = participante.numDocumento,
                escuelaProfesionalId = participante.escuelaProfesional?.id,
                tipoParticipanteId = participante.tipoParticipante?.id,
                congresoId = participante.congreso?.id,
                qrCode = participante.qr_code
            )
        }

        return ResponseEntity.ok(dataResponseParticipantes)
    }

    @GetMapping("/search/apellidos/{apPaterno}/{apMaterno}")
    fun searchParticipanteByApellidos(
        @PathVariable apPaterno: String,
        @PathVariable apMaterno: String
    ): ResponseEntity<List<ParticipanteBusquedaDTO>> {
        val participantes = participanteService.searchByApellidos(apPaterno, apMaterno)

        val dataResponseParticipantes: List<ParticipanteBusquedaDTO> = participantes.map { participante ->
            ParticipanteBusquedaDTO(
                nombres = participante.nombres,
                apPaterno = participante.apPaterno,
                apMaterno = participante.apMaterno,
                estado = participante.estado,
                numDocumento = participante.numDocumento,
            )
        }
        return ResponseEntity.ok(dataResponseParticipantes)
    }

    @GetMapping("/search/documento/{numDocumento}")
    fun searchParticipanteByNumDocumento(@PathVariable numDocumento: String): ResponseEntity<ParticipanteBusquedaDTO> {
        // Buscar el participante por número de documento
        val participante: Participante? = participanteService.searchByNumDocumento(numDocumento)

        // Mapear a ParticipanteBusquedaDTO
        val dataResponseParticipante: ParticipanteBusquedaDTO = ParticipanteBusquedaDTO(
            nombres = participante?.nombres!!,
            apPaterno = participante.apPaterno,
            apMaterno = participante.apMaterno,
            estado = participante.estado,
            numDocumento = participante.numDocumento,
        )

        return ResponseEntity.ok(dataResponseParticipante)
    }

    @GetMapping("/search/nombres/{nombres}")
    fun searchParticipanteByNombres(@PathVariable nombres: String): ResponseEntity<List<ParticipanteBusquedaDTO>> {
        // Buscar participantes por nombres
        val participantes: List<Participante> = participanteService.searchByNombres(nombres)

        // Mapear a lista de ParticipanteBusquedaDTO
        val dataResponseParticipantes: List<ParticipanteBusquedaDTO> = participantes.map { participante ->
            ParticipanteBusquedaDTO(
                nombres = participante.nombres,
                apPaterno = participante.apPaterno,
                apMaterno = participante.apMaterno,
                estado = participante.estado,
                numDocumento = participante.numDocumento,
            )
        }
        return ResponseEntity.ok(dataResponseParticipantes)
    }
}