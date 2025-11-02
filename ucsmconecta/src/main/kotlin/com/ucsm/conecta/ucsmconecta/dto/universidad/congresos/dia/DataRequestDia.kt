package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

data class DataRequestDia(
    @get:NotNull(message = "La 'fecha' es obligatoria")
    @get:FutureOrPresent(message = "La fecha debe mayor o igual a la fecha actual")
    val fecha: LocalDate,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
){}
