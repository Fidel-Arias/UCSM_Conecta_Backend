package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos

import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import java.time.LocalDate

data class DataResponseCongreso(
    val id: Long,
    val codigo: String,
    val nombre: String,
    val fechaInicio: LocalDate,
    val fechaFin: LocalDate,
    val numAsistencias: Int,
    val numRefrigerios: Int,
    val escuelaProfesional: DataResponseEscuelaProfesional?
)
