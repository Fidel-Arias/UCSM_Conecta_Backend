package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.valoracion

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.valoracion.Comentario
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios.DataRequestComentario
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios.DataResponseComentario
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.valoracion.ComentarioService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/congresos/comentario")
class ComentarioController @Autowired constructor(
    private val comentarioService: ComentarioService,
    private val messagingTemplate: SimpMessagingTemplate
) {
    // Endpoint para crear el comentario
    @PostMapping
    fun createComentario(@RequestBody @Valid dataRequestComentario: DataRequestComentario, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseComentario> {
        // Uso del servicio para guardar el comentario
        val comentario = comentarioService.createComentario(dataRequestComentario)

        // Mapear el comentario en DataResponseComentario
        val dataResponseComentario = DataResponseComentario(
            id = comentario.id!!,
            comentario = comentario.texto,
            fecha = comentario.fecha,
            hora = comentario.hora,
            participante = DataResultParticipante(
                nombres = comentario.participante.nombres,
                apPaterno = comentario.participante.apPaterno,
                apMaterno = comentario.participante.apMaterno,
                numDocumento = comentario.participante.numDocumento
            )
        )

        // Enviar el comentario a todos los clientes conectados
        messagingTemplate.convertAndSend("/topic/comentarios", dataResponseComentario)

        // Construir la URI del nuevo recurso creado
        val location = uriComponentsBuilder.path("/api/congresos/comentario/{id}")
            .buildAndExpand(comentario.id).toUri()

        // Retornar la respuesta con el código 201 Created y el cuerpo de la ponencia creada
        return ResponseEntity.created(location).body(dataResponseComentario)
    }

    // Obtener todos los comentarios
    @GetMapping
    fun getAllComentarios(): ResponseEntity<List<DataResponseComentario>> {
        val comentarios: List<Comentario> = comentarioService.getALlComentarios()

        if (comentarios.isEmpty())
            return ResponseEntity.noContent().build()

        val dataResponseComentario = comentarios.map { comentario ->
            DataResponseComentario(
                id = comentario.id!!,
                comentario = comentario.texto,
                fecha = comentario.fecha,
                hora = comentario.hora,
                participante = DataResultParticipante(
                    nombres = comentario.participante.nombres,
                    apPaterno = comentario.participante.apPaterno,
                    apMaterno = comentario.participante.apMaterno,
                    numDocumento = comentario.participante.numDocumento
                )
            )
        }

        return ResponseEntity.ok(dataResponseComentario)
    }
}