package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes.DataResultPonente

data class DataResponsePonencia(
    val id: Long,
    val nombre: String,
    val estado: Boolean,
    val ponente: DataResultPonente,
    val congreso: DataResultCongreso
)
