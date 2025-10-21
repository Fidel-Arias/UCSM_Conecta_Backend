package com.ucsm.conecta.ucsmconecta.controller.universidad.gradoacademico

import com.ucsm.conecta.ucsmconecta.dto.universidad.gradoacademico.DataRequestGradoAcademico
import com.ucsm.conecta.ucsmconecta.dto.universidad.gradoacademico.DataResponseGradoAcademico
import com.ucsm.conecta.ucsmconecta.services.universidad.gradoacademico.GradoAcademicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/grados-academicos")
class GradoAcademicoController @Autowired constructor(
    private val gradoAcademicoService: GradoAcademicoService
) {
    @PostMapping
    @Transactional
    fun createGradoAcademico(@RequestBody @Valid dataRequestGradoAcademico: DataRequestGradoAcademico, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseGradoAcademico> {
        // Lógica para crear un grado académico
        val gradoAcademico = gradoAcademicoService.createGradoAcademico(dataRequestGradoAcademico)

        // Se pasan los datos creados a DataResponseGradoAcademico para visualizarlos
        val dataResponseGradoAcademico = DataResponseGradoAcademico(
            id = gradoAcademico.id!!,
            descripcion = gradoAcademico.descripcion
        )

        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/grados-academicos/{id}")
            .buildAndExpand(gradoAcademico.id)
            .toUri()

        // Retornar la respuesta con el código de estado 201 Created y la ubicación del nuevo recurso
        return ResponseEntity.created(uri).body(dataResponseGradoAcademico)
    }

    @GetMapping
    fun getAllGradosAcademicos(): List<DataResponseGradoAcademico> {
        val gradosAcademicos = gradoAcademicoService.getAllGradosAcademicos()
        return gradosAcademicos.map { grado ->
            DataResponseGradoAcademico(
                id = grado.id!!,
                descripcion = grado.descripcion
            )
        }
    }

    @GetMapping
    fun getGradoAcademicoById(id: Long): ResponseEntity<DataResponseGradoAcademico> {
        // Lógica para obtener un grado académico por su ID
        val gradoAcademico = gradoAcademicoService.searchById(id)
        val dataResponseGradoAcademico = DataResponseGradoAcademico(
            id = gradoAcademico.id!!,
            descripcion = gradoAcademico.descripcion
        )
        return ResponseEntity.ok(dataResponseGradoAcademico)
    }
}