package com.ucsm.conecta.ucsmconecta.dto.users.profile.ponentes

import com.ucsm.conecta.ucsmconecta.domain.universidad.gradoacademico.GradoAcademico
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestPonente(
    @get:NotNull(message = "El nombre es obligatorio")
    @get:NotBlank(message = "El nombre no puede estar en blanco")
    @get:NotEmpty(message = "El nombre no puede estar vacio")
    val nombres: String,

    @get:NotNull(message = "Los 'apellidos' son obligatorios")
    @get:NotBlank(message = "Los 'apellidos' no pueden estar en blanco")
    @get:NotEmpty(message = "Los 'apellidos' no pueden estar vacio")
    val apellidos: String,

    @get:NotNull(message = "El 'grado academico' es obligatorio")
    val gradoAcademicoId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
)
