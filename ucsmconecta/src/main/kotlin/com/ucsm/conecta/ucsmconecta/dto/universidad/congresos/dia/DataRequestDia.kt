package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

data class DataRequestDia(
    @get:NotNull(message = "La 'fecha' es obligatoria")
    val fecha: LocalDate,

    @get:NotNull(message = "El 'estado' es obligatorio")
    val estado: Boolean,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
){}
