package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataResultDiaAsistencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResultPonencia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion.DataResultUbicacion
import java.time.LocalTime

data class DataResultBloque(
    val id: Long,
    val horaInicial: LocalTime,
    val horaFinal: LocalTime,
    val dia: DataResultDiaAsistencia,
    val ubicacion: DataResultUbicacion,
    val ponencia: DataResultPonencia
)
