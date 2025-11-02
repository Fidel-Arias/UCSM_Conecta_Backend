package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import java.time.LocalDate
import java.time.LocalTime

data class DataResponseRefrigerio(
    val id: Long,
    val fecha: LocalDate,
    val hora: LocalTime,
    val participante: DataResultParticipante,
    val congreso: DataResultCongreso
){}
