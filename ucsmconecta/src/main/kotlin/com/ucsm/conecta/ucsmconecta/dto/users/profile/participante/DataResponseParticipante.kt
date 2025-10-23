package com.ucsm.conecta.ucsmconecta.dto.users.profile.participante

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso

data class DataResponseParticipante(
    val id: Long,
    val nombres: String,
    val apPaterno: String,
    val apMaterno: String,
    val estado: String,
    val nDocumento: String,
    val tipoParticipanteId: Long?,
    val escuelaProfesionalId: Long?,
    val congresoId: Long?,
    private val qrCode: String?
)