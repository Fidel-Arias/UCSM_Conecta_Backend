package com.ucsm.conecta.ucsmconecta.dto.universidad.gradoacademico

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestGradoAcademico(
    @get:NotNull(message = "La descripcion es obligatoria")
    @get:NotBlank(message = "La descripcion no puede estar en blanco")
    @get:NotEmpty(message = "La descripcion no puede estar vacia")
    val descripcion: String
)
