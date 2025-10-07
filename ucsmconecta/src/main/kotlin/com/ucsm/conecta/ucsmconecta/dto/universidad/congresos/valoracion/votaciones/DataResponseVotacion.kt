package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones

data class DataResponseVotacion(
    val id: Long,
    val score: Int,
    val participanteId: Long,
    val ponenciaId: Long,
    val congresoId: Long,
)
