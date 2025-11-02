package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataResultBloque
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import java.time.LocalDate
import java.time.LocalTime

data class DataResponseAsistencia(
    val id: Long,
    val fecha: LocalDate,
    val hora: LocalTime,
    val participante: DataResultParticipante,
    val bloque: DataResultBloque,
    val congreso: DataResultCongreso
)
