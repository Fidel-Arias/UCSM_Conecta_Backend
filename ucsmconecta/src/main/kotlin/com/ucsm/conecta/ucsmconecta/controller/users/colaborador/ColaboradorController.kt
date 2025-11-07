package com.ucsm.conecta.ucsmconecta.controller.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import com.ucsm.conecta.ucsmconecta.domain.users.colaborador.Colaborador
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia.DataRequestAsistencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia.DataRequestAsistenciaByNumDocumento
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataResponseBloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResultDia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResultPonencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio.DataRequestRefrigerio
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio.DataResponseRefrigerio
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResultUbicacion
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.DataResponseColaborador
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.DataResponseColaboradorWithCongreso
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.DataResultColaborador
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.ponentes.DataResultPonente
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.asistencia.AsistenciaService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.bloques.BloqueService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.refrigerio.RefrigerioService
import com.ucsm.conecta.ucsmconecta.services.users.ColaboradorService
import com.ucsm.conecta.ucsmconecta.services.users.CongresoColaboradorService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/colaborador")
class ColaboradorController @Autowired constructor(
    private val congresoColaboradorService: CongresoColaboradorService,
    private val colaboradorService: ColaboradorService,
    private val asistenciaService: AsistenciaService,
    private val refrigerioService: RefrigerioService,
    private val bloqueService: BloqueService
){
    /******** ENDPOINTS PARA LA ENTIDAD COLABORADOR ********/
    // Endpoint para obtener un colaborador por su id
    @GetMapping("/profile/{id}")
    fun getColaboradorById(@PathVariable id: Long): ResponseEntity<DataResponseColaborador> {
        val colaborador: Colaborador = colaboradorService.getColaboradorById(id)

        if (!colaborador.estado) {
            return ResponseEntity.noContent().build()
        }

        val dataResponseColaborador = DataResponseColaborador(
            id = colaborador.id!!,
            nombres = colaborador.nombres,
            aPaterno = colaborador.aPaterno,
            aMaterno = colaborador.aMaterno,
            email = colaborador.email,
            estado = colaborador.estado,
            escuelaProfesional = DataResponseEscuelaProfesional(
                id = colaborador.escuelaProfesional.id!!,
                nombre = colaborador.escuelaProfesional.nombre,
            )
        )

        return ResponseEntity.ok(dataResponseColaborador)
    }

    /******** ENDPOINTS PARA LA ENTIDAD COLABORADOR_CONGRESO ********/
    @GetMapping("/{id}")
    fun getColaboradorWithCongresoById(@PathVariable id: Long): ResponseEntity<DataResponseColaboradorWithCongreso> {
        val colaboradorCongreso = congresoColaboradorService.getColaboradorWithCongresoById(id)

        // Mapear la entidad colaboradorCongreso
        val dataResponseColaboradorWithCongreso = DataResponseColaboradorWithCongreso(
            colaborador = DataResultColaborador(
                id = colaboradorCongreso.colaborador.id!!,
                nombres = colaboradorCongreso.colaborador.nombres,
                escuelaProfesional = DataResponseEscuelaProfesional(
                    id = colaboradorCongreso.colaborador.escuelaProfesional.id!!,
                    nombre = colaboradorCongreso.colaborador.escuelaProfesional.nombre
                )
            ),
            congreso = DataResultCongreso(
                id = colaboradorCongreso.congreso.id!!,
                nombre = colaboradorCongreso.congreso.nombre
            )
        )

        return ResponseEntity.ok(dataResponseColaboradorWithCongreso)
    }

    /******** ENDPOINTS PARA LA ENTIDAD ASISTENCIA ********/
    // Endpoint para registrar la asistencia de los participantes por QR
    @PostMapping("/registrar-asistencia/qr")
    fun createAsistenciaWithQR(@RequestBody @Valid dataRequestAsistencia: DataRequestAsistencia): ResponseEntity<Map<String, String>> {
        // creacion de la asistencia con el servicio
        val asistencia = asistenciaService.createAsistenciaWithQR(dataRequestAsistencia)
        val response: Map<String, String> = mapOf("success" to "Registro exitoso")

        return ResponseEntity.ok(response)
    }

    // Endpoint para registrar la asistencia de los participantes por numDocumento
    @PostMapping("/registrar-asistencia/form")
    fun createAsistenciaWithNumDocumento(@RequestBody @Valid dataRequestAsistenciaByNumDocumento: DataRequestAsistenciaByNumDocumento): ResponseEntity<Map<String, String>> {
        // creacion de la asistencia con el servicio
        val asistencia = asistenciaService.createAsistenciaWithNumDocumento(dataRequestAsistenciaByNumDocumento)
        val response: Map<String, String> = mapOf("success" to "Registro exitoso")

        return ResponseEntity.ok(response)
    }

    /******** ENDPOINTS PARA LA ENTIDAD BLOQUE ********/
    @GetMapping("/bloques")
    fun getAllBloquesByDia(): ResponseEntity<List<DataResponseBloque>> {
        val bloques: List<Bloque> = bloqueService.getAllBloquesByDia()

        if (bloques.isEmpty())
            return ResponseEntity.noContent().build()

        val dataResponseBloques: List<DataResponseBloque> = bloques.map { bloque ->
            DataResponseBloque(
                id = bloque.id!!,
                horaInicial = bloque.horaInicio,
                horaFinal = bloque.horaFinal,
                dia = DataResultDia(
                    id = bloque.dia.id!!,
                    fecha = bloque.dia.fecha
                ),
                ubicacion = DataResultUbicacion(
                    id = bloque.ubicacion.id!!,
                    nombre = bloque.ubicacion.nombre,
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

        return ResponseEntity.ok(dataResponseBloques)
    }

    /******** ENDPOINTS PARA LA ENTIDAD REFRIGERIO ********/
    // Endpoint para crear refrigerio del participante por QR
    @PostMapping("/registrar-refrigerio/qr")
    fun createRefrigerioWithQR(@RequestBody @Valid dataRequestRefrigerio: DataRequestRefrigerio, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<Map<String, String>> {
        // Crear refrigerio
        val refrigerio = refrigerioService.createRefrigerioWithQR(dataRequestRefrigerio)

        // Mapear a DataResponseRefrigerio
        val response: Map<String, String> = mapOf("success" to "Registro exitoso")

        // Retornar respuesta con el nuevo recurso creado
        return ResponseEntity.ok(response)
    }

    // Endpoint para crear refrigerio del participante por numero de documento
    @PostMapping("/registrar-refrigerio/form")
    fun createRefrigerioWithNumDocumento(@RequestBody @Valid dataRequestRefrigerio: DataRequestRefrigerio, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<Map<String, String>> {
        // Crear refrigerio
        val refrigerio = refrigerioService.createRefrigerioWithQR(dataRequestRefrigerio)

        // Mapear a DataResponseRefrigerio
        val response: Map<String, String> = mapOf("success" to "Registro exitoso")

        // Retornar respuesta con el nuevo recurso creado
        return ResponseEntity.ok(response)
    }

    // Endpoint para obtener el refrigerio por numDocumento de Participante
    @GetMapping("/refrigerio/search")
    fun searchRefrigerioByNumDocumento(@RequestParam numDocumento: String): ResponseEntity<DataResponseRefrigerio> {
        // Obtener refrigerio
        val refrigerio = refrigerioService.getRefrigerioParticipanteByNumDocumento(numDocumento)

        // Mapear a DataResponseRefrigerio
        val dataResponseRefrigerio = DataResponseRefrigerio(
            id = refrigerio.id!!,
            fecha = refrigerio.fecha,
            hora = refrigerio.hora,
            participante = DataResultParticipante(
                nombres = refrigerio.participante.nombres,
                apPaterno = refrigerio.participante.apPaterno,
                apMaterno = refrigerio.participante.apMaterno,
                numDocumento = refrigerio.participante.numDocumento
            ),
            congreso = DataResultCongreso(
                id = refrigerio.congreso.id!!,
                nombre = refrigerio.congreso.nombre,
            )
        )
        // Retornar respuesta con el refrigerio
        return ResponseEntity.ok(dataResponseRefrigerio)
    }
}