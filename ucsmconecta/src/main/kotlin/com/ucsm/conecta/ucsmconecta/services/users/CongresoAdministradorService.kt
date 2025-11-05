package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.CongresoAdministrador
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.users.admin.CongresoAdministradorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CongresoAdministradorService @Autowired constructor(
    private val congresoAdministradorRepository: CongresoAdministradorRepository
) {
    // Metodo para buscar Administrador y congreso asociados
    fun getAdministradorWithCongresoById(id: Long): CongresoAdministrador = congresoAdministradorRepository.findById(id).orElseThrow { ResourceNotFoundException("Administrador y Congreso no encontrados") }
}