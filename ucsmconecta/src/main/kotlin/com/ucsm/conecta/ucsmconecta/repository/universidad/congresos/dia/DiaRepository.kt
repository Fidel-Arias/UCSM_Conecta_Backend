package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.dia.Dia
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import org.springframework.data.jpa.repository.JpaRepository

interface DiaRepository: JpaRepository<Dia, Long> {
    fun findAllByEstadoTrueOrderByIdAsc(): List<Dia>
}