package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class DataRequestVotacion(
    @get:NotNull(message = "El puntaje es obligatorio")
    @get:Min(value = 0, message = "El puntaje mínimo es de 0")
    @get:Max(value = 5, message = "El puntaje máximo es de 5")
    val score: Int = 0,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    val participanteId: Long,

    @get:NotNull(message = "El id de la 'ponencia' es obligatorio")
    val ponenciaId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long,
)
