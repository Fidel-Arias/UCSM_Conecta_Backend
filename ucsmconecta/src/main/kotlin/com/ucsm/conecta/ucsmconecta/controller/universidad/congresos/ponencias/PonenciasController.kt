package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.ponencias

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataRequestPonencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResponsePonencia
import com.ucsm.conecta.ucsmconecta.dto.users.profile.ponentes.DataResultPonente
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.ponencias.PonenciaService
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/congresos/ponencias")
class PonenciasController @Autowired constructor(
    private val ponenciasService: PonenciaService
) {
    // Metodo para crear una nueva ponencia
    @PostMapping
    fun createPonencia(@RequestBody @Valid dataRequestPonencia: DataRequestPonencia, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponsePonencia> {
        // Crear la ponencia utilizando el servicio
        val ponencia: Ponencia = ponenciasService.createPonencia(dataRequestPonencia)

        // Mapear la entidad Ponencia a DataResponsePonencia
        val dataResponsePonencia = DataResponsePonencia(
            id = ponencia.id!!,
            nombre = ponencia.nombre,
            estado = ponencia.estado,
            ponente = DataResultPonente(
                id = ponencia.ponente.id!!,
                nombres = ponencia.ponente.nombres,
                apellidos = ponencia.ponente.apellidos,
            ),
            congreso = DataResultCongreso(
                id = ponencia.congreso.id!!,
                nombre = ponencia.congreso.nombre,
            )
        )

        // Construir la URI del recurso creado
        val uri = uriComponentsBuilder.path("/api/ponencias/{id}")
            .buildAndExpand(ponencia.id).toUri()

        // Retornar la respuesta con el código 201 Created y el cuerpo de la ponencia creada
        return ResponseEntity.created(uri).body(dataResponsePonencia)
    }

    // Metodo para buscar una ponencia por su id
    @GetMapping("/{id}")
    fun searchPonenciasById(@PathVariable id: Long): ResponseEntity<DataResponsePonencia> {
        val ponencia: Ponencia = ponenciasService.getPonenciaById(id)

        val dataResponsePonencia = DataResponsePonencia(
            id = ponencia.id!!,
            nombre = ponencia.nombre,
            estado = ponencia.estado,
            ponente = DataResultPonente(
                id = ponencia.ponente.id!!,
                nombres = ponencia.ponente.nombres,
                apellidos = ponencia.ponente.apellidos,
            ),
            congreso = DataResultCongreso(
                id = ponencia.congreso.id!!,
                nombre = ponencia.congreso.nombre,
            )
        )

        return ResponseEntity.ok(dataResponsePonencia)
    }

    // Metodo para obtener todas las ponencias activas
    @GetMapping
    fun getAllPonencias(): ResponseEntity<List<DataResponsePonencia>> {
        val ponencias: List<Ponencia> = ponenciasService.getAllPonencias()

        val dataResponsePonencias = ponencias.map { ponencia ->
            DataResponsePonencia(
                id = ponencia.id!!,
                nombre = ponencia.nombre,
                estado = ponencia.estado,
                ponente = DataResultPonente(
                    id = ponencia.ponente.id!!,
                    nombres = ponencia.ponente.nombres,
                    apellidos = ponencia.ponente.apellidos,
                ),
                congreso = DataResultCongreso(
                    id = ponencia.congreso.id!!,
                    nombre = ponencia.congreso.nombre,
                )
            )
        }

        return ResponseEntity.ok(dataResponsePonencias)
    }

    // Metodo para buscar una ponencia por su nombre
    @GetMapping("/search/nombre")
    fun getPonenciaByNombre(@RequestParam nombre: String): ResponseEntity<DataResponsePonencia> {
        val ponencia: Ponencia = ponenciasService.getPonenciaByNombre(nombre)

        val dataResponsePonencia = DataResponsePonencia(
            id = ponencia.id!!,
            nombre = ponencia.nombre,
            estado = ponencia.estado,
            ponente = DataResultPonente(
                id = ponencia.ponente.id!!,
                nombres = ponencia.ponente.nombres,
                apellidos = ponencia.ponente.apellidos,
            ),
            congreso = DataResultCongreso(
                id = ponencia.congreso.id!!,
                nombre = ponencia.congreso.nombre,
            )
        )

        return ResponseEntity.ok(dataResponsePonencia)
    }

    // Metodo para desactivar una ponencia por su ID
    @DeleteMapping("/deactivate/{id}")
    fun deactivatePonencia(@PathVariable id: Long): ResponseEntity<Void> {
        val ponencia: Ponencia = ponenciasService.deactivatePonencia(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para activar una ponencia por su ID
    @PutMapping("/activate/{id}")
    fun activatePonencia(@PathVariable id: Long): ResponseEntity<DataResponsePonencia> {
        ponenciasService.activatePonencia(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para actualizar una ponencia por su ID
    @PutMapping("/{id}")
    fun updatePonencia(@PathVariable id: Long, @RequestBody @Valid dataRequestPonencia: DataRequestPonencia): ResponseEntity<DataResponsePonencia> {
        // Actualizar la ponencia utilizando el servicio
        val ponencia: Ponencia = ponenciasService.updatePonencia(id, dataRequestPonencia)
        // Mapear la entidad Ponencia a DataResponsePonencia
        val dataResponsePonencia = DataResponsePonencia(
            id = ponencia.id!!,
            nombre = ponencia.nombre,
            estado = ponencia.estado,
            ponente = DataResultPonente(
                id = ponencia.ponente.id!!,
                nombres = ponencia.ponente.nombres,
                apellidos = ponencia.ponente.apellidos,
            ),
            congreso = DataResultCongreso(
                id = ponencia.congreso.id!!,
                nombre = ponencia.congreso.nombre,
            )
        )
        return ResponseEntity.ok(dataResponsePonencia)
    }
}