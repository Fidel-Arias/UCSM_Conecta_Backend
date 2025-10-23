package com.ucsm.conecta.ucsmconecta.controller.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataRequestCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResponseCongreso
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/congresos")
class CongresoController @Autowired constructor(
    private val congresoService: CongresoService,
    private val escuelaProfesionalService: EscuelaProfesionalService
) {
    @PostMapping
    @Transactional
    fun createCongreso(@RequestBody @Valid dataRequestCongreso: DataRequestCongreso, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseCongreso> {
        // Creacion del congreso
        val congreso: Congreso? = congresoService.createCongreso(dataRequestCongreso)

        // Buscar la escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.getEscuelaProfesionalById(dataRequestCongreso.escuelaProfesionalId)

        // Se pasan los datos creados a DataResponseCongreso para visualizarlos
        val dataResponseCongreso = DataResponseCongreso(
            id = congreso?.id!!,
            nombre = congreso.nombre,
            fechaInicio = congreso.fechaInicio,
            fechaFin = congreso.fechaFin,
            numAsistencias = congreso.numAsistencias,
            numRefrigerios = congreso.numRefrigerios,
            escuelaProfesionalId = escuelaProfesional?.id!!
        )

        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/congresos/{id}")
            .buildAndExpand(congreso.id).toUri()

        return ResponseEntity.created(uri).body(dataResponseCongreso)
    }

    @GetMapping("/{id}")
    fun getCongresoById(@PathVariable id: Long): ResponseEntity<DataResponseCongreso> {
        val congreso: Congreso? = congresoService.getCongresoById(id)

        // Buscar la escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.getEscuelaProfesionalById(congreso?.escuelaProfesional?.id!!)

        // Se pasan los datos a DataResponseCongreso para visualizarlos
        val dataResponseCongreso = DataResponseCongreso(
            id = congreso?.id!!,
            nombre = congreso.nombre,
            fechaInicio = congreso.fechaInicio,
            fechaFin = congreso.fechaFin,
            numAsistencias = congreso.numAsistencias,
            numRefrigerios = congreso.numRefrigerios,
            escuelaProfesionalId = escuelaProfesional?.id!!
        )

        return ResponseEntity.ok(dataResponseCongreso)
    }
}