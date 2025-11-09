package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class DataRequestRefrigerio(
    @get:NotNull(message = "El documento del 'participante' es obligatorio")
    @get:NotBlank(message = "El documento del 'participante' no puede estar en blanco")
    @get:NotEmpty(message = "El documento del 'participante' no puede estar vacio")
    @get:Size(min = 8, message = "El documento es de al menos 8 caracteres")
    val documentoParticipante: String,

    @get:NotNull(message = "El codigo del 'congreso' es obligatorio")
    @get:NotBlank(message = "El codigo del 'congreso' no puede estar en blanco")
    @get:NotEmpty(message = "El codigo del 'congreso' no puede estar vacio")
    @get:Size(min = 8, message = "El codigo es de al menos 8 caracteres")
    val congresoCod: String
)
