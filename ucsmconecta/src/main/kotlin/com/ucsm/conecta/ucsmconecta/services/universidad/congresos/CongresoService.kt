package com.ucsm.conecta.ucsmconecta.services.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataRequestCongreso
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.CongresoRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class CongresoService @Autowired constructor(
    private val congresoRepository: CongresoRepository,
    private val escuelaProfesionalService: EscuelaProfesionalService
){
    fun getCongresoById(id: Long):Congreso = congresoRepository.findById(id)
        .orElseThrow { ResourceNotFoundException("Congreso con id $id no encontrado") }

    fun searchByNombre(nombre: String):Congreso = congresoRepository.findByNombre(nombre)
        .orElseThrow { ResourceNotFoundException("Congreso no encontrado") }

    @Transactional
    fun createCongreso(@RequestBody @Valid dataRequestCongreso: DataRequestCongreso): Congreso {
        val escuelaProfesional: EscuelaProfesional = escuelaProfesionalService.searchEscuelaProfesionalById(
            dataRequestCongreso.escuelaProfesionalId
        )

        return congresoRepository.save(
            Congreso(
                nombre = dataRequestCongreso.nombre,
                fechaInicio = dataRequestCongreso.fechaInicio,
                fechaFin = dataRequestCongreso.fechaFin,
                numAsistencias = dataRequestCongreso.numAsistencias,
                numRefrigerios = dataRequestCongreso.numRefrigerios,
                escuelaProfesional = escuelaProfesional
            )
        )
    }
}