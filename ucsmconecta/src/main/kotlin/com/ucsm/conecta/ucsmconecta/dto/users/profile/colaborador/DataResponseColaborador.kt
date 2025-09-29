package com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional

data class DataResponseColaborador(
    private val id: Long,
    val nombres: String,
    val aPaterno: String,
    val aMaterno: String,
    private val estado: Boolean,
    val escuelaProfesional: EscuelaProfesional
)
