package com.ucsm.conecta.ucsmconecta.services.create.universidad.congresos

import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.CongresoRepository
import org.springframework.beans.factory.annotation.Autowired

class CongresoService @Autowired constructor(
    private val congresoRepository: CongresoRepository
){
    fun searchById(id: Long) = congresoRepository.findById(id).orElseThrow { RuntimeException("Congreso no encontrado") }
}