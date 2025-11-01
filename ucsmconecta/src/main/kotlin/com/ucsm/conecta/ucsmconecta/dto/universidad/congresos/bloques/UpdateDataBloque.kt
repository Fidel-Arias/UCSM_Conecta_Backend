package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.FutureOrPresent
import java.time.LocalTime

data class UpdateDataBloque(
    @get:FutureOrPresent(message = "La hora de inicio no puede estar en el pasado")
    val horaInicial: LocalTime? = null,

    @get:Future(message = "La 'hora final' debe ser mayor a la hora inicial")
    val horaFinal: LocalTime? = null,

    val ubicacionId: Long? = null,
    val ponenciaId: Long? = null
)
