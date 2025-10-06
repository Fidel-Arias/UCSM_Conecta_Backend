package com.ucsm.conecta.ucsmconecta.services.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.CongresoRepository
import org.springframework.beans.factory.annotation.Autowired

class CongresoService @Autowired constructor(
    private val congresoRepository: CongresoRepository
){
    fun searchById(id: Long):Congreso = congresoRepository.findById(id)
        .orElseThrow { RuntimeException("Congreso no encontrado") }

    fun searchByNombre(nombre: String):Congreso = congresoRepository.findByNombre(nombre)
        .orElseThrow { RuntimeException("Congreso no encontrado") }
}