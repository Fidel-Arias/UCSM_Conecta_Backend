package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.bloques

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataRequestBloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataResponseBloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.UpdateDataBloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResultDia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResultPonencia
import com.ucsm.conecta.ucsmconecta.dto.users.profile.ponentes.DataResultPonente
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResultUbicacion
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.bloques.BloqueService
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
@RequestMapping("/api/congresos/bloques")
class BloqueController @Autowired constructor(
    private val bloqueService: BloqueService
) {
    // Endpoint para crear un nuevo bloque
    @PostMapping
    fun createBloque(@RequestBody @Valid dataRequestBloque: DataRequestBloque, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseBloque> {
        // Llamar al servicio para crear el bloque
        val nuevoBloque: Bloque = bloqueService.createBloque(dataRequestBloque)

        // Construir la respuesta con el URI del nuevo recurso
        val location = uriComponentsBuilder.path("/api/congresos/bloques/{id}")
            .buildAndExpand(nuevoBloque.id).toUri()

        // Crear el cuerpo de la respuesta
        val dataResponseBloque = DataResponseBloque(
            id = nuevoBloque.id!!,
            horaInicial = nuevoBloque.horaInicio,
            horaFinal = nuevoBloque.horaFinal,
            dia = DataResultDia(
                id = nuevoBloque.dia.id!!,
                fecha = nuevoBloque.dia.fecha
            ),
            ubicacion = DataResultUbicacion(
                id = nuevoBloque.ubicacion.id!!,
                nombre = nuevoBloque.ubicacion.nombre,
            ),
            ponencia = DataResultPonencia(
                id = nuevoBloque.ponencia.id!!,
                nombre = nuevoBloque.ponencia.nombre,
                ponente = DataResultPonente(
                    id = nuevoBloque.ponencia.ponente.id!!,
                    nombres = nuevoBloque.ponencia.ponente.nombres,
                    apellidos = nuevoBloque.ponencia.ponente.apellidos
                )
            )
        )
        return ResponseEntity.created(location).body(dataResponseBloque)
    }

    // Endpoint para obtener todos los bloques
    @GetMapping
    fun getAllBloques(): ResponseEntity<List<DataResponseBloque>> {
        val bloques: List<Bloque> = bloqueService.getAllBloques()

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

    // Endpoint para obtener un bloque por su id
    @GetMapping("/{id}")
    fun searchBloqueById(@PathVariable id: Long): ResponseEntity<DataResponseBloque> {
        val bloque: Bloque = bloqueService.getBloqueById(id)
        val dataResponseBloque = DataResponseBloque(
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
        return ResponseEntity.ok(dataResponseBloque)
    }

    // Endpoint para eliminar un bloque por su id
    @DeleteMapping("/{id}")
    fun deleteBloqueById(@PathVariable id: Long): ResponseEntity<Void> {
        bloqueService.deleteBloqueById(id)
        return ResponseEntity.noContent().build()
    }

    // Endpoint para actualizar un bloque por su id
    @PutMapping("/{id}")
    fun updateBloque(
        @PathVariable id: Long,
        @RequestBody @Valid updateDataBloque: UpdateDataBloque
    ): ResponseEntity<Void> {
        val bloqueActualizado: Bloque = bloqueService.updateBloque(id, updateDataBloque)
        return ResponseEntity.noContent().build()
    }
}