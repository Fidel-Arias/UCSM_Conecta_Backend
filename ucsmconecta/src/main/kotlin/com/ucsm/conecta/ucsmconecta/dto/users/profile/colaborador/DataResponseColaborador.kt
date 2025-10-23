package com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional

data class DataResponseColaborador(
    val id: Long,
    val nombres: String,
    val aPaterno: String,
    val aMaterno: String,
    val email: String,
    val estado: Boolean,
    val escuelaProfesional: Long?
)
