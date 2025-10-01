package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalTime

data class DataRequestBloque(
    @get:NotNull(message = "La 'hora de inicio' es obligatoria")
    @get:NotBlank(message = "La 'hora de inicio' no puede estar vacía")
    @get:NotEmpty(message = "La 'hora de inicio' no puede estar vacía")
    val horaInicial: String,

    @get:NotNull(message = "La 'hora final' es obligatoria")
    @get:NotBlank(message = "La 'hora final' no puede estar vacía")
    @get:NotEmpty(message = "La 'hora final' no puede estar vacía")
    val horaFinal: String,

    @get:NotNull(message = "El id del 'día' es obligatorio")
    @get:NotBlank(message = "El id del 'día' no puede estar vacío")
    @get:NotEmpty(message = "El id del 'día' no puede estar vacío")
    val diaId: Long,

    @get:NotNull(message = "El id de la 'ubicación' es obligatorio")
    @get:NotBlank(message = "El id de la 'ubicación' no puede estar vacío")
    @get:NotEmpty(message = "El id de la 'ubicación' no puede estar vacío")
    val ubicacionId: Long,

    @get:NotNull(message = "El id de la 'ponencia' es obligatorio")
    @get:NotBlank(message = "El id de la 'ponencia' no puede estar vacío")
    @get:NotEmpty(message = "El id de la 'ponencia' no puede estar vacío")
    val ponenciaId: Long
)
