package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class DataRequestRefrigerioWithNumDocumento(
    @get:NotNull(message = "El número de documento es obligatorio")
    @get:NotBlank(message = "El número de documento no puede estar en blanco")
    @get:NotEmpty(message = "El número de documento no puede estar vacío")
    val numDocumento: String,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
)
