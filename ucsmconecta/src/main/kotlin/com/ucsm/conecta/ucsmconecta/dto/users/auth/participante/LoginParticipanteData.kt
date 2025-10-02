package com.ucsm.conecta.ucsmconecta.dto.users.auth.participante

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class LoginParticipanteData(
    @get:NotNull(message = "El documento es obligatorio")
    @get:NotEmpty(message = "El numero del documento no puede estar vacio" )
    @get:NotBlank(message = "El numero del documento no puede estar en blanco")
    @get:Size(min = 8, message = "El numero del documento debe tener al menos 8 caracteres")
    @get:Size(max = 12, message = "El numero del documento debe tener como maximo 12 caracteres")
    val numDocumento: String,
) {
}
