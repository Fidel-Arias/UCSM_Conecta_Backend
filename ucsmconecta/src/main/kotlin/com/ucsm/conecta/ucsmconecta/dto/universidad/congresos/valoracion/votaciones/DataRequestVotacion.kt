package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class DataRequestVotacion(
    @get:NotNull(message = "El puntaje es obligatorio")
    @get:Min(value = 0, message = "El puntaje mínimo es de 0")
    @get:Max(value = 5, message = "El puntaje máximo es de 5")
    val score: Int = 0,

    @get:NotNull(message = "El número de documento es obligatorio")
    @get:NotBlank(message = "El número de documento no puede estar en blanco")
    @get:NotEmpty(message = "El número de documento no puede estar vacío")
    @get:Size(min = 8, message = "El documento es de al menos 8 caracteres")
    val documentoParticipante: String,

    @get:NotNull(message = "El id de la 'ponencia' es obligatorio")
    val ponenciaId: Long,

    @get:NotNull(message = "El codigo del 'congreso' es obligatorio")
    @get:NotBlank(message = "El codigo del 'congreso' no puede estar en blanco")
    @get:NotEmpty(message = "El codigo del 'congreso' no puede estar vacio")
    @get:Size(min = 8, message = "El codigo es de al menos 8 caracteres")
    val congresoCod: String
)
