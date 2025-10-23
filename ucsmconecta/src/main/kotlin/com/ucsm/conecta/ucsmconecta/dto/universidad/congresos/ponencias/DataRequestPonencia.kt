package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestPonencia(
    @get:NotNull(message = "El 'nombre' es obligatorio")
    @get:NotBlank(message = "El 'nombre' no puede estar vacío")
    @get:NotEmpty(message = "El 'nombre' no puede estar vacío")
    val nombre: String,

    @get:NotNull(message = "El 'estado' es obligatorio")
    val estado: Boolean,

    @get:NotNull(message = "El id del 'ponente' es obligatorio")
    val ponenteID: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
){}
