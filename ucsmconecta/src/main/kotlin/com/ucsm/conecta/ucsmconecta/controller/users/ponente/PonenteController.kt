package com.ucsm.conecta.ucsmconecta.controller.users.ponente

import com.ucsm.conecta.ucsmconecta.domain.users.ponente.Ponente
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes.DataRequestPonente
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes.DataResponsePonente
import com.ucsm.conecta.ucsmconecta.dto.universidad.gradoacademico.DataResponseGradoAcademico
import com.ucsm.conecta.ucsmconecta.services.users.PonenteService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/ponentes")
class PonenteController @Autowired constructor(
    private val ponenteService: PonenteService
) {
    // Metodo para crear una nuevo ponente
    @PostMapping
    fun createPonente(@RequestBody @Valid dataRequestPonente: DataRequestPonente, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponsePonente> {
        // Crear la ponencia utilizando el servicio
        val ponente: Ponente = ponenteService.createPonente(dataRequestPonente)

        // Mapear la entidad Ponencia a DataResponsePonencia
        val dataResponsePonente = DataResponsePonente(
            id = ponente.id!!,
            nombres = ponente.nombres,
            apellidos = ponente.apellidos,
            gradoAcademico = DataResponseGradoAcademico(
                id = ponente.gradoAcademico.id!!,
                descripcion = ponente.gradoAcademico.descripcion
            ),
            congreso = DataResultCongreso(
                id = ponente.congreso.id!!,
                nombre = ponente.congreso.nombre,
            )
        )

        // Construir la URI del recurso creado
        val uri = uriComponentsBuilder.path("/api/ponentes/{id}")
             .buildAndExpand(ponente.id).toUri()

        // Retornar la respuesta con el código 201 Created y el cuerpo de la ponencia creada
        return ResponseEntity.created(uri).body(dataResponsePonente)
    }

    // Metodo para buscar un ponente por su id
    @GetMapping("/{id}")
    fun searchPonenteById(@PathVariable id: Long): ResponseEntity<DataResponsePonente> {
        val ponente: Ponente = ponenteService.getPonenteById(id)

        val dataResponsePonente = DataResponsePonente(
            id = ponente.id!!,
            nombres = ponente.nombres,
            apellidos = ponente.apellidos,
            gradoAcademico = DataResponseGradoAcademico(
                id = ponente.gradoAcademico.id!!,
                descripcion = ponente.gradoAcademico.descripcion
            ),
            congreso = DataResultCongreso(
                id = ponente.congreso.id!!,
                nombre = ponente.congreso.nombre,
            )
        )

        return ResponseEntity.ok(dataResponsePonente)
    }

    // Metodo para listar todos los ponentes
    @GetMapping
    fun listAllPonentes(): ResponseEntity<List<DataResponsePonente>> {
        val ponentes: List<Ponente> = ponenteService.getAllPonentes()

        if (ponentes.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        val dataResponsePonentes = ponentes.map { ponente ->
            DataResponsePonente(
                id = ponente.id!!,
                nombres = ponente.nombres,
                apellidos = ponente.apellidos,
                gradoAcademico = DataResponseGradoAcademico(
                    id = ponente.gradoAcademico.id!!,
                    descripcion = ponente.gradoAcademico.descripcion
                ),
                congreso = DataResultCongreso(
                    id = ponente.congreso.id!!,
                    nombre = ponente.congreso.nombre,
                )
            )
        }

        return ResponseEntity.ok(dataResponsePonentes)
    }

    // Metodo para eliminar un ponente por su id
    @DeleteMapping("/{id}")
    fun deletePonenteById(@PathVariable id: Long): ResponseEntity<Void> {
        ponenteService.deletePonente(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para actualizar un ponente por su id
    @PutMapping("/{id}")
    fun updatePonenteById(@PathVariable id: Long, @RequestBody @Valid dataRequestPonente: DataRequestPonente): ResponseEntity<DataResponsePonente> {
        val ponente: Ponente = ponenteService.updatePonente(id, dataRequestPonente)

        val dataResponsePonente = DataResponsePonente(
            id = ponente.id!!,
            nombres = ponente.nombres,
            apellidos = ponente.apellidos,
            gradoAcademico = DataResponseGradoAcademico(
                id = ponente.gradoAcademico.id!!,
                descripcion = ponente.gradoAcademico.descripcion
            ),
            congreso = DataResultCongreso(
                id = ponente.congreso.id!!,
                nombre = ponente.congreso.nombre,
            )
        )

        return ResponseEntity.ok(dataResponsePonente)
    }
}