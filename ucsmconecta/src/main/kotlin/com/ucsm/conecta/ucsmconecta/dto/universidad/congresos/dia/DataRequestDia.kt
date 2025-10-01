package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

data class DataRequestDia(
    @get:NotNull(message = "La 'fecha' es obligatoria")
    @get:NotBlank(message = "La 'fecha' no puede estar vacía")
    @get:NotEmpty(message = "La 'fecha' no puede estar vacía")
    val fecha: LocalDate,

    @get:NotNull(message = "El 'estado' es obligatorio")
    @get:NotBlank(message = "El 'estado' no puede estar vacío")
    @get:NotEmpty(message = "El 'estado' no puede estar vacío")
    val estado: Boolean,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    @get:NotBlank(message = "El id del 'congreso' no puede estar vacío")
    @get:NotEmpty(message = "El id del 'congreso' no puede estar vacío")
    val congresoId: Long
){}
