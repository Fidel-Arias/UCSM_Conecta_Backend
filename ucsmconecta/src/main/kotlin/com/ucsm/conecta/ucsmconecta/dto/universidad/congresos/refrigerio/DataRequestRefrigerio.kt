package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

data class DataRequestRefrigerio(
    @get:NotNull(message = "La 'fecha' es obligatoria")
    @get:FutureOrPresent(message = "La 'fecha' debe ser hoy o en adelante")
    val fecha: LocalDate,

    @get:NotNull(message = "El 'estado' es obligatorio")
    val estado: Boolean,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    val participanteId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
){}
