package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalTime

data class DataRequestBloque(
    @get:NotNull(message = "La 'hora de inicio' es obligatoria")
    @get:FutureOrPresent(message = "La hora de inicio no puede estar en el pasado")
    val horaInicial: LocalTime,

    @get:NotNull(message = "La 'hora final' es obligatoria")
    @get:Future(message = "La 'hora final' debe ser mayor a la hora inicial")
    val horaFinal: LocalTime,

    @get:NotNull(message = "El id del 'día' es obligatorio")
    val diaId: Long,

    @get:NotNull(message = "El id de la 'ubicación' es obligatorio")
    val ubicacionId: Long,

    @get:NotNull(message = "El id de la 'ponencia' es obligatorio")
    val ponenciaId: Long
)
