package com.ucsm.conecta.ucsmconecta.dto.users.profile.participante

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional

data class DataResponseParticipante(
    private val id: Long,
    val nombres: String,
    val aPaterno: String,
    val aMaterno: String,
    private val estado: Boolean,
    val nDocumento: String,
    val escuelaProfesional: EscuelaProfesional,
    private val qrCode: String
) {
}