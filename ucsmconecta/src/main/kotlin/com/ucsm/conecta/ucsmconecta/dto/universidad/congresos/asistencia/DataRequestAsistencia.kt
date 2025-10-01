package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate
import java.time.LocalTime

data class DataRequestAsistencia(
    @get:NotNull(message = "La 'fecha' es obligatoria")
    @get:NotBlank(message = "La 'fecha' no puede estar en blanco")
    @get:NotEmpty(message = "La 'fecha' no puede estar vacía")
    val fecha: LocalDate,

    @get:NotNull(message = "La 'hora' es obligatoria")
    @get:NotBlank(message = "La 'hora' no puede estar en blanco")
    @get:NotEmpty(message = "La 'hora' no puede estar vacía")
    val hora: LocalTime,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    @get:NotBlank(message = "El id del 'participante' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'participante' no puede estar vacío")
    val participanteId: Long,

    @get:NotNull(message = "El id del 'bloque' es obligatorio")
    @get:NotBlank(message = "El id del 'bloque' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'bloque' no puede estar vacío")
    val bloqueId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    @get:NotBlank(message = "El id del 'congreso' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'congreso' no puede estar vacío")
    val congresoId: Long
) {
}