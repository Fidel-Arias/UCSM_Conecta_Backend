package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.asistencia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.asistencia.Asistencia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AsistenciaRepository: JpaRepository<Asistencia, Long> {
    fun existsByParticipanteIdAndBloqueIdAndCongresoId(
        participanteId: Long,
        bloqueId: Long,
        congresoId: Long
    ): Boolean
}