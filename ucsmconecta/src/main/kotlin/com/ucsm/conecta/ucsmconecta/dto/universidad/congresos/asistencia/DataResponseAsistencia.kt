package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia

data class DataResponseAsistencia(
    val id: Long,
    val fecha: String,
    val hora: String,
    val participanteId: Long,
    val bloqueId: Long,
    val congresoId: Long
)
