package com.ucsm.conecta.ucsmconecta.services.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.CongresoRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CongresoService @Autowired constructor(
    private val congresoRepository: CongresoRepository
){
    fun searchById(id: Long):Congreso? = congresoRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Congreso no encontrado") }

    fun searchByNombre(nombre: String):Congreso? = congresoRepository.findByNombre(nombre)
        .orElseThrow { EntityNotFoundException("Congreso no encontrado") }
}