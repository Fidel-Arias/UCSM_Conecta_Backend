package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResultDia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResultUbicacion
import java.time.LocalTime

data class DataResultBloque(
    val id: Long,
    val horaInicial: LocalTime,
    val horaFinal: LocalTime,
    val dia: DataResultDia,
    val ubicacion: DataResultUbicacion,
)
