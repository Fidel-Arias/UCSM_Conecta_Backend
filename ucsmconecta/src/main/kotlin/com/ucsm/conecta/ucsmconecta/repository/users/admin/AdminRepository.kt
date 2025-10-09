package com.ucsm.conecta.ucsmconecta.repository.users.admin

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AdminRepository: JpaRepository<Administrador, Long> {
    @Query(
        value = "SELECT * FROM buscar_admin_nombres(:nombres)",
        nativeQuery = true)
    fun findByNombres(@Param("nombres") nombres: String): List<Administrador>
    fun findByEscuelaProfesionalId(escuelaProfesionalId: Long): List<Administrador>
}