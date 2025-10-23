package com.ucsm.conecta.ucsmconecta.controller.universidad.carrera

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataRequestEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
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
@RequestMapping("/api/escuelas-profesionales")
class EscuelaProfesionalController @Autowired constructor(
    private val escuelaProfesionalService: EscuelaProfesionalService
) {
    @PostMapping
    @Transactional
    fun createEscuelaProfesional(@RequestBody @Valid dataRequestEscuelaProfesional: DataRequestEscuelaProfesional, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseEscuelaProfesional> {
        // Lógica para crear una escuela profesional
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.createEscuelaProfesional(dataRequestEscuelaProfesional)

        // Se pasan los datos creados a DataResponseEscuelaProfesional para visualizarlos
        val dataResponseEscuelaProfesional: DataResponseEscuelaProfesional? = DataResponseEscuelaProfesional(
            id = escuelaProfesional?.id!!,
            nombre = escuelaProfesional.nombre
        )

        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/escuelas-profesionales/{id}")
            .buildAndExpand(escuelaProfesional.id)
            .toUri()

        // Retornar la respuesta con el código de estado 201 Created y la ubicación del nuevo recurso
        return ResponseEntity.created(uri).body(dataResponseEscuelaProfesional)
    }

    @GetMapping
    fun getAllEscuelasProfesionales(): ResponseEntity<List<DataResponseEscuelaProfesional>> {
        val escuelasProfesionales = escuelaProfesionalService.getAllEscuelasProfesionales()

        if (escuelasProfesionales.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        val dataResponseEscuelaProfesional = escuelasProfesionales.map { escuela ->
            DataResponseEscuelaProfesional(
                id = escuela.id!!,
                nombre = escuela.nombre
            )
        }
        return ResponseEntity.ok(dataResponseEscuelaProfesional)
    }

    @GetMapping("/{id}")
    fun getEscuelaProfesionalById(@PathVariable id: Long): ResponseEntity<DataResponseEscuelaProfesional> {
        // Lógica para obtener una escuela profesional por su ID
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.getEscuelaProfesionalById(id)

        // Se pasan los datos obtenidos a DataResponseEscuelaProfesional para visualizarlos
        val dataResponseEscuelaProfesional: DataResponseEscuelaProfesional? = DataResponseEscuelaProfesional(
            id = escuelaProfesional?.id!!,
            nombre = escuelaProfesional.nombre
        )

        // Retornar la respuesta con el código de estado 200 OK y los datos de la escuela profesional
        return ResponseEntity.ok(dataResponseEscuelaProfesional)
    }
}