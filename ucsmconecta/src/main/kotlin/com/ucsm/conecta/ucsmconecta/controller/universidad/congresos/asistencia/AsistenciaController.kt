package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.asistencia

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia.DataRequestAsistencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia.DataResponseAsistencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataResultBloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResultDia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResultUbicacion
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.asistencia.AsistenciaService
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
@RequestMapping("/api/congresos/asistencia")
class AsistenciaController @Autowired constructor(
    private val asistenciaService: AsistenciaService
) {
    @PostMapping
    fun createAsistencia(@RequestBody @Valid dataRequestAsistencia: DataRequestAsistencia, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseAsistencia> {
        // creacion de la asistencia con el servicio
        val asistencia = asistenciaService.createAsistencia(dataRequestAsistencia)

        // Mapear el resultado a DataResponseAsistencia
        val dataResponseAsistencia = DataResponseAsistencia(
            id = asistencia.id!!,
            fecha = asistencia.fecha,
            hora = asistencia.hora,
            participante = DataResultParticipante(
                nombres = asistencia.participante.nombres,
                apPaterno = asistencia.participante.apPaterno,
                apMaterno = asistencia.participante.apMaterno,
                numDocumento = asistencia.participante.numDocumento
            ),
            bloque = DataResultBloque(
                id = asistencia.bloque.id!!,
                horaInicial = asistencia.bloque.horaInicio,
                horaFinal = asistencia.bloque.horaFinal,
                dia = DataResultDia(
                    id = asistencia.bloque.dia.id!!,
                    fecha = asistencia.bloque.dia.fecha
                ),
                ubicacion = DataResultUbicacion(
                    id = asistencia.bloque.ubicacion.id!!,
                    nombre = asistencia.bloque.ubicacion.nombre
                )
            ),
            congreso = DataResultCongreso(
                id = asistencia.congreso.id!!,
                nombre = asistencia.congreso.nombre
            )
        )

        // Crear la URI
        val location = uriComponentsBuilder.path("/api/congresos/asistencia/{id}")
            .buildAndExpand(asistencia.id).toUri()

        return ResponseEntity.created(location).body(dataResponseAsistencia)
    }

    @GetMapping("/{id}")
    fun searchAsistenciaById(@PathVariable id: Long): ResponseEntity<DataResponseAsistencia> {
        val asistencia = asistenciaService.getAsistenciaById(id)

        // Mapear el resultado a DataResponseAsistencia
        val dataResponseAsistencia = DataResponseAsistencia(
            id = asistencia.id!!,
            fecha = asistencia.fecha,
            hora = asistencia.hora,
            participante = DataResultParticipante(
                nombres = asistencia.participante.nombres,
                apPaterno = asistencia.participante.apPaterno,
                apMaterno = asistencia.participante.apMaterno,
                numDocumento = asistencia.participante.numDocumento
            ),
            bloque = DataResultBloque(
                id = asistencia.bloque.id!!,
                horaInicial = asistencia.bloque.horaInicio,
                horaFinal = asistencia.bloque.horaFinal,
                dia = DataResultDia(
                    id = asistencia.bloque.dia.id!!,
                    fecha = asistencia.bloque.dia.fecha
                ),
                ubicacion = DataResultUbicacion(
                    id = asistencia.bloque.ubicacion.id!!,
                    nombre = asistencia.bloque.ubicacion.nombre
                )
            ),
            congreso = DataResultCongreso(
                id = asistencia.congreso.id!!,
                nombre = asistencia.congreso.nombre
            )
        )

        return ResponseEntity.ok(dataResponseAsistencia)
    }
}