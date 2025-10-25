package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.bloques

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import org.springframework.data.jpa.repository.JpaRepository

interface BloqueRepository: JpaRepository<Bloque, Long> {
}