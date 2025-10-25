package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.ubicacion

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ubicacion.Ubicacion
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataRequestUbicacion
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResponseUbicacion
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.ubicacion.UbicacionService
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
@RequestMapping("/api/ubicaciones")
class UbicacionController @Autowired constructor(
    private val ubicacionService: UbicacionService
) {
    // Metodo para crear una nueva Ubicacion
    @PostMapping
    fun createUbicacion(@RequestBody @Valid dataRequestUbicacion: DataRequestUbicacion, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseUbicacion> {
        // Crear una nueva Ubicacion
        val ubicacion: Ubicacion = ubicacionService.createUbicacion(dataRequestUbicacion)

        // Mapear la ubicacion creada a un DataResponseUbicacion
        val dataResponseUbicacion = DataResponseUbicacion(
            id = ubicacion.id!!,
            nombre = ubicacion.nombre,
            estado = ubicacion.estado
        )

        // Construir la URI del recurso creado
        val uri = uriComponentsBuilder.path("/api/ubicaciones/{id}")
            .buildAndExpand(dataResponseUbicacion.id)
            .toUri()

        // Retornar la respuesta con el recurso creado
        return ResponseEntity.created(uri).body(dataResponseUbicacion)
    }

    // Metodo para buscar una Ubicacion por su ID
    @GetMapping("/{id}")
    fun searchUbicacionById(@PathVariable id: Long): ResponseEntity<DataResponseUbicacion> {
        // Buscar la Ubicacion por su ID
        val ubicacion: Ubicacion = ubicacionService.getUbicacionById(id)

        if (!ubicacion.estado) {
            return ResponseEntity.notFound().build()
        }

        // Mapear la ubicacion encontrada a un DataResponseUbicacion
        val dataResponseUbicacion = DataResponseUbicacion(
            id = ubicacion.id!!,
            nombre = ubicacion.nombre,
            estado = ubicacion.estado
        )

        // Retornar la respuesta con el recurso encontrado
        return ResponseEntity.ok(dataResponseUbicacion)
    }

    // Metodo para obtener todas las Ubicaciones
    @GetMapping
    fun getAllUbicaciones(): ResponseEntity<List<DataResponseUbicacion>> {
        // Obtener todas las Ubicaciones
        val ubicaciones: List<Ubicacion> = ubicacionService.getAllUbicaciones()

        // Mapear las ubicaciones a una lista de DataResponseUbicacion
        val dataResponseUbicaciones = ubicaciones.map { ubicacion ->
            DataResponseUbicacion(
                id = ubicacion.id!!,
                nombre = ubicacion.nombre,
                estado = ubicacion.estado
            )
        }

        // Retornar la respuesta con la lista de recursos encontrados
        return ResponseEntity.ok(dataResponseUbicaciones)
    }

    // Metodo para desactivar una Ubicacion por su ID
    @DeleteMapping("/deactivate/{id}")
    fun deactivateUbicacion(id: Long): ResponseEntity<Void> {
        // Desactivar la Ubicacion por su ID
        ubicacionService.deactivateUbicacion(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para activar una Ubicacion por su ID
    @PutMapping("/activate/{id}")
    fun activateUbicacion(id: Long): ResponseEntity<Void> {
        // Activar la Ubicacion por su ID
        ubicacionService.activateUbicacion(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para actualizar una Ubicacion por su ID
    @PutMapping("/{id}")
    fun updateUbicacion(@PathVariable id: Long, @RequestBody @Valid dataRequestUbicacion: DataRequestUbicacion): ResponseEntity<DataResponseUbicacion> {
        // Actualizar la Ubicacion por su ID
        val ubicacion: Ubicacion = ubicacionService.updateUbicacion(id, dataRequestUbicacion)

        // Mapear la ubicacion actualizada a un DataResponseUbicacion
        val dataResponseUbicacion = DataResponseUbicacion(
            id = ubicacion.id!!,
            nombre = ubicacion.nombre,
            estado = ubicacion.estado
        )
        // Retornar la respuesta con el recurso actualizado
        return ResponseEntity.ok(dataResponseUbicacion)
    }
}