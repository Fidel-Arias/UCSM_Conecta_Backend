package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.refrigerio.Refrigerio
import org.springframework.data.jpa.repository.JpaRepository

interface RefrigerioRepository: JpaRepository<Refrigerio, Long> {
    fun countByParticipanteAndCongreso(participanteId: Long, congresoId: Long): Int
}