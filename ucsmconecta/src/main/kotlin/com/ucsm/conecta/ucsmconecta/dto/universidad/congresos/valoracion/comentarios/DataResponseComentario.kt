package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios

import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante
import java.time.LocalDate
import java.time.LocalTime

data class DataResponseComentario(
    val id: Long,
    val comentario: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val participante: DataResultParticipante
)
