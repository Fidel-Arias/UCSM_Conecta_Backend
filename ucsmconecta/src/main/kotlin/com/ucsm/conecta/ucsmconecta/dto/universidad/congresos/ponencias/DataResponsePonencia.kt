package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias

data class DataResponsePonencia(
    val id: Long,
    val nombre: String,
    val estado: Boolean,
    val ponenteID: Long,
    val congresoId: Long
)
