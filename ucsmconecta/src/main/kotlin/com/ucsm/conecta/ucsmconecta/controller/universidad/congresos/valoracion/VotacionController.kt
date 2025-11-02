package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.valoracion

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.valoracion.Votacion
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResultPonencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones.DataRequestVotacion
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones.DataResponseVotacion
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.ponentes.DataResultPonente
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.valoracion.VotacionService
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
@RequestMapping("/api/congresos/votacion")
class VotacionController @Autowired constructor(
    private val votacionService: VotacionService
) {
    // Endpoint para crear una Votacion
    @PostMapping
    fun createVotacion(@RequestBody @Valid dataRequestVotacion: DataRequestVotacion, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseVotacion> {
        // Uso del servicio votacionService
        val votacion = votacionService.createVotacion(dataRequestVotacion)

        // Mapear la votacion en DataResponseVotacion
        val dataResponseVotacion = DataResponseVotacion(
            id = votacion.id!!,
            score = votacion.score,
            participante = DataResultParticipante(
                nombres = votacion.participante.nombres,
                apPaterno = votacion.participante.apPaterno,
                apMaterno = votacion.participante.apMaterno,
                numDocumento = votacion.participante.numDocumento
            ),
            ponencia = DataResultPonencia(
                id = votacion.ponencia.id!!,
                nombre = votacion.ponencia.nombre,
                ponente = DataResultPonente(
                    id = votacion.ponencia.ponente.id!!,
                    nombres = votacion.ponencia.ponente.nombres,
                    apellidos = votacion.ponencia.ponente.apellidos
                )
            ),
            congreso = DataResultCongreso(
                id = votacion.congreso.id!!,
                nombre = votacion.congreso.nombre
            )
        )

        // Construir la URI del recurso creado
        val uri = uriComponentsBuilder.path("/api/congresos/votacion/{id}")
            .buildAndExpand(votacion.id).toUri()

        // Retornar la respuesta con el código 201 Created y el cuerpo de la ponencia creada
        return ResponseEntity.created(uri).body(dataResponseVotacion)
    }

    //Endpoint para obtener la votacion por ID
    @GetMapping("/{id}")
    fun searchVotacionById(@PathVariable id: Long): ResponseEntity<DataResponseVotacion> {
        val votacion: Votacion = votacionService.getVotacionById(id)

        val dataResponseVotacion = DataResponseVotacion(
            id = votacion.id!!,
            score = votacion.score,
            participante = DataResultParticipante(
                nombres = votacion.participante.nombres,
                apPaterno = votacion.participante.apPaterno,
                apMaterno = votacion.participante.apMaterno,
                numDocumento = votacion.participante.numDocumento
            ),
            ponencia = DataResultPonencia(
                id = votacion.ponencia.id!!,
                nombre = votacion.ponencia.nombre,
                ponente = DataResultPonente(
                    id = votacion.ponencia.ponente.id!!,
                    nombres = votacion.ponencia.ponente.nombres,
                    apellidos = votacion.ponencia.ponente.apellidos
                )
            ),
            congreso = DataResultCongreso(
                id = votacion.congreso.id!!,
                nombre = votacion.congreso.nombre
            )
        )

        // Retornar la respuesta con el código 200 ok
        return ResponseEntity.ok(dataResponseVotacion)
    }

    @GetMapping
    fun getALlVotaciones(): ResponseEntity<List<DataResponseVotacion>> {
        val votaciones: List<Votacion> = votacionService.getALlVotaciones()

        if (votaciones.isEmpty())
            return ResponseEntity.noContent().build()

        // Mapear la lista de votaciones
        val dataResponseVotacion = votaciones.map { votacion ->
            DataResponseVotacion(
                id = votacion.id!!,
                score = votacion.score,
                participante = DataResultParticipante(
                    nombres = votacion.participante.nombres,
                    apPaterno = votacion.participante.apPaterno,
                    apMaterno = votacion.participante.apMaterno,
                    numDocumento = votacion.participante.numDocumento
                ),
                ponencia = DataResultPonencia(
                    id = votacion.ponencia.id!!,
                    nombre = votacion.ponencia.nombre,
                    ponente = DataResultPonente(
                        id = votacion.ponencia.ponente.id!!,
                        nombres = votacion.ponencia.ponente.nombres,
                        apellidos = votacion.ponencia.ponente.apellidos
                    )
                ),
                congreso = DataResultCongreso(
                    id = votacion.congreso.id!!,
                    nombre = votacion.congreso.nombre
                )
            )
        }

        return ResponseEntity.ok(dataResponseVotacion)
    }
}