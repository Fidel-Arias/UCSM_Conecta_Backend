package com.ucsm.conecta.ucsmconecta.repository.universidad.carrera

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface EscuelaProfesionalRepository: JpaRepository<EscuelaProfesional, Long> {
    fun findByNombre(nombre: String): Optional<EscuelaProfesional>
}