package com.ucsm.conecta.ucsmconecta.repository.users.admin

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Administrador, Long> {
    fun findByNombres(nombres: String): List<Administrador>
    fun findByEscuelaProfesionalId(escuelaProfesionalId: Long): List<Administrador>
}