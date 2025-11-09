package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class DataRequestDia(
    @get:NotNull(message = "La 'fecha' es obligatoria")
    @get:FutureOrPresent(message = "La fecha debe mayor o igual a la fecha actual")
    val fecha: LocalDate,

    @get:NotNull(message = "El codigo del 'congreso' es obligatorio")
    @get:NotBlank(message = "El codigo del 'congreso' no puede estar en blanco")
    @get:NotEmpty(message = "El codigo del 'congreso' no puede estar vacio")
    @get:Size(min = 8, message = "El codigo es de al menos 8 caracteres")
    val congresoCod: String
){}
