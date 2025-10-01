package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques

data class DataResponseBloque(
    val id: Long,
    val horaInicial: String,
    val horaFinal: String,
    val diaId: Long,
    val ubicacionId: Long,
    val ponenciaId: Long
)
