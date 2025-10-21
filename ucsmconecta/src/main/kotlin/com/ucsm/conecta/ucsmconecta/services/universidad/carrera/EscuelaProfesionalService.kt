package com.ucsm.conecta.ucsmconecta.services.universidad.carrera

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataRequestEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.repository.universidad.carrera.EscuelaProfesionalRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.Optional

@Service
class EscuelaProfesionalService @Autowired constructor(
    private val escuelaProfesionalRepository: EscuelaProfesionalRepository
){
    fun searchById(id: Long): EscuelaProfesional? = escuelaProfesionalRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Escuela Profesional no encontrada") }

    fun searchByNombre(nombre: String): EscuelaProfesional? = escuelaProfesionalRepository.findByNombre(nombre)
        .orElseThrow { EntityNotFoundException("Escuela Profesional no encontrada") }

    fun createEscuelaProfesional(@RequestBody @Valid dataRequestEscuelaProfesional: DataRequestEscuelaProfesional): EscuelaProfesional {
        // Lógica para crear una escuela profesional
        return escuelaProfesionalRepository.save(EscuelaProfesional(
            nombre = dataRequestEscuelaProfesional.nombre,
        ))
    }

    fun getAllEscuelasProfesionales(): List<EscuelaProfesional> = escuelaProfesionalRepository.findAll()
}