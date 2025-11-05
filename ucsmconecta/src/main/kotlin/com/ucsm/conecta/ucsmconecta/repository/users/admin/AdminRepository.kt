package com.ucsm.conecta.ucsmconecta.repository.users.admin

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface AdminRepository: JpaRepository<Administrador, Long> {
    fun findByEmail(email: String): Optional<Administrador>
}