package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos

import java.time.LocalDate

data class DataResponseCongreso(
    val id: Long,
    val nombre: String,
    val fechaInicio: LocalDate,
    val fechaFin: LocalDate,
    val numAsistencias: Int,
    val numRefrigerios: Int,
    val escuelaProfesionalId: Long
)
