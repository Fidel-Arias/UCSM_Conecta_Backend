package com.ucsm.conecta.ucsmconecta.repository.users.admin

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AdminRepository: JpaRepository<Administrador, Long> {

}