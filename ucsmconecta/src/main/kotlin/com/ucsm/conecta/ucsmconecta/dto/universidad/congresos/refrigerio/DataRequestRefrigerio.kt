package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio

import jakarta.validation.constraints.NotNull

data class DataRequestRefrigerio(
    @get:NotNull(message = "El id del 'participante' es obligatorio")
    val participanteId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
){}
