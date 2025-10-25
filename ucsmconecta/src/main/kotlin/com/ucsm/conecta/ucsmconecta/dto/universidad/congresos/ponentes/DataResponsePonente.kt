package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.gradoacademico.DataResponseGradoAcademico

data class DataResponsePonente(
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val gradoAcademico: DataResponseGradoAcademico,
    val congreso: DataResultCongreso
)
