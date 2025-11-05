package com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador

import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional

data class DataResultColaborador(
    val nombres: String,
    val escuelaProfesional: DataResponseEscuelaProfesional?
)
