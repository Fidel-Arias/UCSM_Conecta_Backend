package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import java.time.LocalDate

data class DataResultDia(
    val id: Long,
    val fecha: LocalDate,
    val congreso: DataResultCongreso
)
