package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.FutureOrPresent
import java.time.LocalDate

data class DataRequestComentario(
    @get:NotNull(message = "El texto es obligatorio")
    @get:NotBlank(message = "El texto no puede estar en blanco")
    @get:NotEmpty(message = "El texto no puede estar vacio")
    val texto: String,

    @get:NotNull(message = "La fecha es obligatoria")
    @get:NotBlank(message = "La fecha no puede estar en blanco")
    @get:NotEmpty(message = "La fecha no puede estar vacio")
    @get:FutureOrPresent(message = "La fecha debe ser hoy o en adelante")
    val fecha: LocalDate,

    @get:NotNull(message = "El estado es obligatorio")
    @get:NotBlank(message = "El estado no puede estar en blanco")
    @get:NotEmpty(message = "El estado no puede estar vacio")
    val estado: Boolean,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    @get:NotBlank(message = "El id del 'participante' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'participante' no puede estar vacio")
    val participanteId: Long,
)