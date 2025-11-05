package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestAsistenciaByNumDocumento(
    @get:NotNull(message = "El número de documento es obligatorio")
    @get:NotBlank(message = "El número de documento no puede estar en blanco")
    @get:NotEmpty(message = "El número de documento no puede estar vacío")
    val numDocumento: String,

    val bloqueId: Long,

    val congresoId: Long,
)
