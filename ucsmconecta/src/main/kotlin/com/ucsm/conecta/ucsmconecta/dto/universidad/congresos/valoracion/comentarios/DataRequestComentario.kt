package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.comentarios

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class DataRequestComentario(
    @get:NotNull(message = "El texto es obligatorio")
    @get:NotBlank(message = "El texto no puede estar en blanco")
    @get:NotEmpty(message = "El texto no puede estar vacio")
    val texto: String,

    @get:NotNull(message = "El número de documento es obligatorio")
    @get:NotBlank(message = "El número de documento no puede estar en blanco")
    @get:NotEmpty(message = "El número de documento no puede estar vacío")
    @get:Size(min = 8, message = "El documento es de al menos 8 caracteres")
    val documentoParticipante: String,
)