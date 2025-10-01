package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes

data class DataResponsePonente(
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val gradoAcademico: String,
    val congresoId: Long
)
