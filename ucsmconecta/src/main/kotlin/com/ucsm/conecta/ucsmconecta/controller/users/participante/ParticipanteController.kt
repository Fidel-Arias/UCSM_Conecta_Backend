package com.ucsm.conecta.ucsmconecta.controller.users.participante

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.valoracion.Comentario
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.valoracion.Votacion
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataResultBloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResultDiaAsistencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResultPonencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResultUbicacion
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios.DataRequestComentario
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios.DataResponseComentario
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones.DataRequestVotacion
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones.DataResponseVotacion
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResponseParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResponseTipoParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.ponentes.DataResultPonente
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.asistencia.AsistenciaService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.bloques.BloqueService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.valoracion.ComentarioService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.valoracion.VotacionService
import com.ucsm.conecta.ucsmconecta.services.users.ParticipanteService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.Instant
import java.util.Map


@RestController
@RequestMapping("/api/participante")
class ParticipanteController @Autowired constructor(
    private val participanteService: ParticipanteService,
    private val comentarioService: ComentarioService,
    private val votacionService: VotacionService,
    private val messagingTemplate: SimpMessagingTemplate,
    private val bloqueService: BloqueService,
    private val asistenciaService: AsistenciaService
    ) {
    /******** ENDPOINTS PARA OBTENER LA FECHA ********/
    @GetMapping("/time")
    fun getServerTime(): MutableMap<String?, String?> {
        val now = Instant.now().toString() // siempre UTC
        return Map.of<String?, String?>("serverTime", now)
    }
    /******** ENDPOINTS PARA LA ENTIDAD PARTICIPANTE ********/
    // Metodo para obtener un participante por su ID
    @GetMapping("/{id}")
    fun getParticipanteById(@PathVariable id: Long): ResponseEntity<DataResponseParticipante> {
        // Buscar el participante por ID
        val participante: Participante = participanteService.getParticipanteById(id)

        val dataResponseParticipante = DataResponseParticipante(
            id = participante.id!!,
            nombres = participante.nombres,
            apPaterno = participante.apPaterno,
            apMaterno = participante.apMaterno,
            estado = participante.estado,
            numDocumento = participante.numDocumento,
            escuelaProfesional = DataResponseEscuelaProfesional(
                id = participante.escuelaProfesional.id!!,
                nombre = participante.escuelaProfesional.nombre,
                codigo = participante.escuelaProfesional.codigo
            ),
            tipoParticipante = DataResponseTipoParticipante(
                id = participante.tipoParticipante.id!!,
                descripcion = participante.tipoParticipante.descripcion
            ),
            congreso = DataResultCongreso(
                id = participante.congreso.id!!,
                nombre = participante.congreso.nombre,
                codigo = participante.congreso.codigo
            ),
            qrCode = participante.qr_code
        )

        return ResponseEntity.ok(dataResponseParticipante)
    }

    @GetMapping("/contar")
    fun contarAsistencias(
        @RequestParam numDocumento: String,
        @RequestParam congresoCod: String
    ): ResponseEntity<kotlin.collections.Map<String, Any>?> {
        val resultado = asistenciaService.contarAsistencias(numDocumento, congresoCod)

        return ResponseEntity.ok(resultado)
    }

    /******** ENDPOINTS PARA LA ENTIDAD BLOQUE ********/
    // Endpoint para obtener todos los bloques por dia
    @GetMapping("/bloques")
    fun getAllBloquesByDia(): ResponseEntity<List<DataResultBloque>> {
        val bloques: List<Bloque> = bloqueService.getAllBloquesByDia()

        if (bloques.isEmpty())
            return ResponseEntity.noContent().build()

        val dataResponsePonencias = bloques.map { bloque ->
            DataResultBloque(
                id = bloque.id!!,
                horaInicial = bloque.horaInicio,
                horaFinal = bloque.horaFinal,
                dia = DataResultDiaAsistencia(
                    id = bloque.dia.id!!,
                    fecha = bloque.dia.fecha,
                ),
                ubicacion = DataResultUbicacion(
                    id = bloque.ubicacion.id!!,
                    nombre = bloque.ubicacion.nombre
                ),
                ponencia = DataResultPonencia(
                    id = bloque.ponencia.id!!,
                    nombre = bloque.ponencia.nombre,
                    ponente = DataResultPonente(
                        id = bloque.ponencia.ponente.id!!,
                        nombres = bloque.ponencia.ponente.nombres,
                        apellidos = bloque.ponencia.ponente.apellidos
                    )
                )
            )
        }

        return ResponseEntity.ok(dataResponsePonencias)
    }

    /******** ENDPOINTS PARA LA ENTIDAD COMENTARIO ********/
    // Endpoint para crear el comentario
    @PostMapping("/create-comentario")
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
    @GetMapping("/comentarios")
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

    /******** ENDPOINTS PARA LA ENTIDAD VOTACION ********/
    // Endpoint para crear una Votacion
    @PostMapping("/create-votacion")
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
                nombre = votacion.congreso.nombre,
                codigo = votacion.congreso.codigo
            )
        )

        // Construir la URI del recurso creado
        val uri = uriComponentsBuilder.path("/api/congresos/votacion/{id}")
            .buildAndExpand(votacion.id).toUri()

        // Retornar la respuesta con el código 201 Created y el cuerpo de la ponencia creada
        return ResponseEntity.created(uri).body(dataResponseVotacion)
    }

    @GetMapping("/votaciones")
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
                    nombre = votacion.congreso.nombre,
                    codigo = votacion.congreso.codigo
                )
            )
        }

        return ResponseEntity.ok(dataResponseVotacion)
    }
}