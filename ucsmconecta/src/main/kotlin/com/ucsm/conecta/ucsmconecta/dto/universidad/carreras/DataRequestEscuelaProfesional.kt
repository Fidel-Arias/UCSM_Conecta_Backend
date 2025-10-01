package com.ucsm.conecta.ucsmconecta.dto.universidad.carreras

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestEscuelaProfesional(
    @get:NotNull(message = "El nombre es obligatorio")
    @get:NotBlank(message = "El nombre no puede estar en blanco")
    @get:NotEmpty(message = "El nombre no puede estar vacio")
    var nombre: String,
) {
}
