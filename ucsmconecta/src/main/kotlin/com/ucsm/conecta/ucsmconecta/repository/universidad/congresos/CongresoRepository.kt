package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import org.springframework.data.jpa.repository.JpaRepository

interface CongresoRepository: JpaRepository<Congreso, Long> {
    fun findByNombre(nombre: String): Congreso
}