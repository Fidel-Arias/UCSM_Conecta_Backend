package com.ucsm.conecta.ucsmconecta.dto.users.profile.participante

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestTipoParticipante(
    @get:NotNull(message = "La 'descripcion' es obligatoria")
    @get:NotBlank(message = "La 'descripcion' no puede estar en blanco")
    @get:NotEmpty(message = "La 'descripcion' no puede estar vacío")
    val descripcion: String,
)
