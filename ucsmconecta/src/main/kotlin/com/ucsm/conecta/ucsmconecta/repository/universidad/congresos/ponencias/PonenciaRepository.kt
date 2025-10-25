package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.ponencias

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PonenciaRepository: JpaRepository<Ponencia, Long> {
    fun findByNombre(nombre: String): Optional<Ponencia>
}