package com.ucsm.conecta.ucsmconecta.dto.users.profile.participante

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class DataRequestParticipante(
    @get:NotNull(message = "El 'nombre' son obligatorios")
    @get:NotBlank(message = "El 'nombre' no puede estar en blanco")
    @get:NotEmpty(message = "El 'nombre' no puede estar vacío")
    val nombres: String,

    @get:NotNull(message = "El 'apellido paterno' es obligatorio")
    @get:NotBlank(message = "El 'apellido paterno' no puede estar en blanco")
    @get:NotEmpty(message = "El 'apellido paterno' no puede estar vacío")
    val apPaterno: String,

    @get:NotNull(message = "El 'apellido materno' es obligatorio")
    @get:NotBlank(message = "El 'apellido materno' no puede estar en blanco")
    @get:NotEmpty(message = "El 'apellido materno' no puede estar vacío")
    val apMaterno: String,

    @get:NotNull(message = "El 'numero de documento' no puede ser nulo")
    @get:NotBlank(message = "El 'numero de documento' no puede estar en blanco")
    @get:NotEmpty(message = "El 'numero de documento' no puede estar vacío")
    @get:Size(min = 8, max = 12, message = "El 'numero de documento' debe tener entre 8 y 12 caracteres")
    val numDocumento: String,

    @get:NotNull(message = "El 'email' no puede ser nulo")
    @get:NotBlank(message = "El 'email' no puede estar en blanco")
    @get:NotEmpty(message = "El 'email' no puede estar vacío")
    @get:Email(message = "El 'email' debe ser un correo electrónico válido")
    val email: String,

    @get:NotNull(message = "El id del 'tipo participante' es obligatorio")
    @get:NotBlank(message = "El id del 'tipo participante' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'tipo participante' no puede estar vacío")
    val tipoParticipanteId: Long,

    @get:NotNull(message = "El id de la 'escuela profesional' es obligatorio")
    @get:NotBlank(message = "El id de la 'escuela profesional' no puede estar en blanco")
    @get:NotEmpty(message = "El id de la 'escuela profesional' no puede estar vacío")
    val escuelaProfesionalId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    @get:NotBlank(message = "El id del 'congreso' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'congreso' no puede estar vacío")
    val congresoId: Long,

    @get:NotNull(message = "El 'estado' es obligatorio")
    @get:NotBlank(message = "El 'estado' no puede estar en blanco")
    @get:NotEmpty(message = "El 'estado' no puede estar vacío")
    val estado: String,

    val qr_code: String?,
){}
