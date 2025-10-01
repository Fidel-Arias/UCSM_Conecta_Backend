package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.Future
import java.time.LocalDate

data class DataRequestCongreso(
    @get:NotNull(message = "El nombre es obligatorio")
    @get:NotBlank(message = "El nombre no puede estar en blanco")
    @get:NotEmpty(message = "El nombre no puede estar vacio")
    val nombre: String,

    @get:NotNull(message = "La fecha es obligatoria")
    @get:NotBlank(message = "La fecha no puede estar en blanco")
    @get:NotEmpty(message = "La fecha no puede estar vacio")
    @get:FutureOrPresent(message = "La fecha inicial debe ser hoy o en el futuro")
    val fechaInicio: LocalDate,

    @get:NotNull(message = "La fecha es obligatoria")
    @get:NotBlank(message = "La fecha no puede estar en blanco")
    @get:NotEmpty(message = "La fecha no puede estar vacio")
    @get:Future(message = "La fecha final debe ser en el futuro")
    val fechaFin: LocalDate,

    @get:NotNull(message = "El numero total de asistencias es obligatorio")
    @get:NotBlank(message = "El numero total de asistencias no puede estar en blanco")
    @get:NotEmpty(message = "El numero total de asistencias no puede estar vacio")
    val numAsistencias: Int,

    @get:NotNull(message = "El numero de refrigerios es obligatorio")
    @get:NotBlank(message = "El numero de refrigerios no puede estar en blanco")
    @get:NotEmpty(message = "El numero de refrigerios no puede estar vacio")
    val numRefrigerios: Int,

    @get:NotNull(message = "El id de la escuela profesional es obligatorio")
    @get:NotBlank(message = "El id de la escuela profesional no puede estar en blanco")
    @get:NotEmpty(message = "El id de la escuela profesional no puede estar vacio")
    val escuelaProfesionalId: Long
)
