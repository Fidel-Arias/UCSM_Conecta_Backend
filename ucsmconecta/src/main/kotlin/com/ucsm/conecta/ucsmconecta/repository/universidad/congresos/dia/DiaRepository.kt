package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.dia.Dia
import org.springframework.data.jpa.repository.JpaRepository

interface DiaRepository: JpaRepository<Dia, Long> {
}