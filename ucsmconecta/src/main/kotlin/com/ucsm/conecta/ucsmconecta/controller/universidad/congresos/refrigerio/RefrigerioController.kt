package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio.DataRequestRefrigerio
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio.DataResponseRefrigerio
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.refrigerio.RefrigerioService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/congresos/refrigerios")
class RefrigerioController @Autowired constructor(
    private val refrigerioService: RefrigerioService
) {
    // Endpoint para crear refrigerio
    @PostMapping
    fun createRefrigerio(@RequestBody @Valid dataRequestRefrigerio: DataRequestRefrigerio, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseRefrigerio> {
        // Crear refrigerio
        val refrigerio = refrigerioService.createRefrigerio(dataRequestRefrigerio)

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
        // Construir la URI del nuevo recurso creado
        val location = uriComponentsBuilder.path("/api/congresos/refrigerios/{id}")
            .buildAndExpand(refrigerio.id)
            .toUri()
        // Retornar respuesta con el nuevo recurso creado
        return ResponseEntity.created(location).body(dataResponseRefrigerio)
    }

    // Endpoint para obtener el refrigerio por id
    @GetMapping("/{id}")
    fun searchRefrigerioById(@PathVariable id: Long): ResponseEntity<DataResponseRefrigerio> {
        // Obtener refrigerio
        val refrigerio = refrigerioService.getRefrigerioById(id)

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

    // Endpoint para eliminar refrigerio por id
    @DeleteMapping("/{id}")
    fun deleteRefrigerioById(@PathVariable id: Long): ResponseEntity<Void> {
        // Eliminar refrigerio
        refrigerioService.deleteRefrigerioById(id)
        // Retornar respuesta sin contenido
        return ResponseEntity.noContent().build()
    }
}