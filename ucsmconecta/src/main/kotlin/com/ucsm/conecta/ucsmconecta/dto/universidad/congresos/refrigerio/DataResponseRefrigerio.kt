package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio

import java.time.LocalDate

data class DataResponseRefrigerio(
    val id: Long,
    val fecha: LocalDate,
    val estado: Boolean,
    val participanteId: Long,
    val congresoId: Long
){}
