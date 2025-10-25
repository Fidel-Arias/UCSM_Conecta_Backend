package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes.DataResultPonente

data class DataResultPonencia(
    val id: Long,
    val nombre: String,
    val ponente: DataResultPonente
)
