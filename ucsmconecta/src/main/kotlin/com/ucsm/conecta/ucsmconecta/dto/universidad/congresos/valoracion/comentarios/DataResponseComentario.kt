package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios

import java.time.LocalDate

data class DataResponseComentario(
    val id: Long,
    val comentario: String,
    val fecha: LocalDate,
    val estado: Boolean,
    val participanteId: Long
)
