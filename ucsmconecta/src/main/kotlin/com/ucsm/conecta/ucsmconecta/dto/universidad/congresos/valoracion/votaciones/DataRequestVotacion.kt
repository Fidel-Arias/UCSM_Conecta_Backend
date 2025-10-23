package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestVotacion(
    @get:NotNull(message = "El puntaje es obligatorio")
    val score: Int,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    val participanteId: Long,

    @get:NotNull(message = "El id de la 'ponencia' es obligatorio")
    val ponenciaId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long,
)
