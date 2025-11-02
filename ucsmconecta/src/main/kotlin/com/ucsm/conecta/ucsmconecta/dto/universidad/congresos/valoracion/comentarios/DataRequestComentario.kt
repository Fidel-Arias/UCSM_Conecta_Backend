package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestComentario(
    @get:NotNull(message = "El texto es obligatorio")
    @get:NotBlank(message = "El texto no puede estar en blanco")
    @get:NotEmpty(message = "El texto no puede estar vacio")
    val texto: String,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    val participanteId: Long,
)