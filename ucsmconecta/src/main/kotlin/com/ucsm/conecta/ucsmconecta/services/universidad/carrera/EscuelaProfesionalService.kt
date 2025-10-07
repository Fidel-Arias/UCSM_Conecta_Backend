package com.ucsm.conecta.ucsmconecta.services.universidad.carrera

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.repository.universidad.carrera.EscuelaProfesionalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EscuelaProfesionalService @Autowired constructor(
    private val escuelaProfesionalRepository: EscuelaProfesionalRepository
){
    fun searchById(id: Long): EscuelaProfesional = escuelaProfesionalRepository.findById(id)
        .orElseThrow { RuntimeException("Escuela Profesional no encontrada") }

    fun searchByNombre(nombre: String): EscuelaProfesional = escuelaProfesionalRepository.findByNombre(nombre)
        .orElseThrow { RuntimeException("Escuela Profesional no encontrada") }
}