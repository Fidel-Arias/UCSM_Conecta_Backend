package com.ucsm.conecta.ucsmconecta.dto.users.auth.colaborador

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class LoginColaboradorData(
    @get:NotNull(message = "El email es obligatorio")
    @get:NotEmpty(message = "El email no puede estar vacio")
    @get:NotBlank(message = "El email no puede estar en blanco")
    @get:Email(message = "El email debe tener un formato valido")
    val email: String,

    @get:NotNull(message = "La contraseña es obligatoria")
    @get:NotBlank(message = "La contraseña no puede estar en blanco")
    @get:NotEmpty(message = "La contraseña no puede estar vacia")
    @get:Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @get:Size(max = 12, message = "La contraseña debe tener como maximo 12 caracteres")
    private val password: String
){
}
