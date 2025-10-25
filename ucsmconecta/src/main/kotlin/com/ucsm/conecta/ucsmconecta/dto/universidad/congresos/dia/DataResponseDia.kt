package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import java.time.LocalDate

data class DataResponseDia(
    val id: Long,
    val fecha: LocalDate,
    val estado: Boolean,
    val congreso: DataResultCongreso
)
