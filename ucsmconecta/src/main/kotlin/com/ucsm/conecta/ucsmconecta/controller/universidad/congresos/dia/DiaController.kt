package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataRequestDia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResponseDia
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.dia.DiaService
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
@RequestMapping("/api/congresos/dias")
class DiaController @Autowired constructor(
    private val diaService: DiaService
) {
    // Endpoint para crear un nuevo día
    @PostMapping
    fun createDia(@RequestBody @Valid dataRequestDia: DataRequestDia, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseDia> {
        // Crear el nuevo día utilizando el servicio
        val diaCreado = diaService.createDia(dataRequestDia)

        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/congresos/dias/{id}").buildAndExpand(diaCreado.id).toUri()

        // Mapear a DataResponseDia y devolver la respuesta con el código 201 Created
        val dataResponseDia = DataResponseDia(
            id = diaCreado.id!!,
            fecha = diaCreado.fecha,
            estado = diaCreado.estado,
            congreso = DataResultCongreso(
                id = diaCreado.congreso.id!!,
                nombre = diaCreado.congreso.nombre
            )
        )

        // Retornar la respuesta con el código 201 Created
        return ResponseEntity.created(uri).body(dataResponseDia)
    }

    // Endpoint para buscar un día por su ID
    @GetMapping("/{id}")
    fun searchDiaById(@PathVariable id: Long): ResponseEntity<DataResponseDia> {
        val dia = diaService.getDiaById(id)

        if (!dia.estado)
            return ResponseEntity.noContent().build()

        val dataResponseDia = DataResponseDia(
            id = dia.id!!,
            fecha = dia.fecha,
            estado = dia.estado,
            congreso = DataResultCongreso(
                id = dia.congreso.id!!,
                nombre = dia.congreso.nombre
            )
        )

        return ResponseEntity.ok(dataResponseDia)
    }

    // Endpoint para listar todos los días
    @GetMapping
    fun listAllDias(): ResponseEntity<List<DataResponseDia>> {
        val dias = diaService.getAllDias()

        if (dias.isEmpty())
            return ResponseEntity.noContent().build()

        val dataResultDias = dias.map { dia ->
            DataResponseDia(
                id = dia.id!!,
                fecha = dia.fecha,
                estado = dia.estado,
                congreso = DataResultCongreso(
                    id = dia.congreso.id!!,
                    nombre = dia.congreso.nombre
                )
            )
        }
        return ResponseEntity.ok(dataResultDias)
    }

    // Endpoint para desactivar un día
    @DeleteMapping("/deactivate/{id}")
    fun deactivateDia(@PathVariable id: Long): ResponseEntity<Void> {
        diaService.deactivateDia(id)
        return ResponseEntity.noContent().build()
    }

    // Endpoint para activar un día
    @PutMapping("/activate/{id}")
    fun activateDia(@PathVariable id: Long): ResponseEntity<Void> {
        diaService.activateDia(id)
        return ResponseEntity.noContent().build()
    }
}