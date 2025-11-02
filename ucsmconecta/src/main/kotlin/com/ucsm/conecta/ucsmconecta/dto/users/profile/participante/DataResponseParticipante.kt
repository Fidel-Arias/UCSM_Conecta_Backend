package com.ucsm.conecta.ucsmconecta.dto.users.profile.participante

import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso

data class DataResponseParticipante(
    val id: Long,
    val nombres: String,
    val apPaterno: String,
    val apMaterno: String,
    val estado: String,
    val numDocumento: String,
    val tipoParticipante: DataResponseTipoParticipante?,
    val escuelaProfesional: DataResponseEscuelaProfesional?,
    val congreso: DataResultCongreso?,
    private val qrCode: String?
)