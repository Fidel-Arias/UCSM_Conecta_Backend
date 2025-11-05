package com.ucsm.conecta.ucsmconecta.repository.users.admin

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.CongresoAdministrador
import org.springframework.data.jpa.repository.JpaRepository

interface CongresoAdministradorRepository: JpaRepository<CongresoAdministrador, Long> {
}