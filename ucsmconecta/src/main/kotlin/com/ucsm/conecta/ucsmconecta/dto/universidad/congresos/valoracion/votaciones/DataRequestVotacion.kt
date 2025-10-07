package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestVotacion(
    @get:NotNull(message = "El puntaje es obligatorio")
    @get:NotBlank(message = "El puntaje no puede estar en blanco")
    @get:NotEmpty(message = "El puntaje no puede estar vacio")
    val score: Int,

    @get:NotNull(message = "El id del 'participante' es obligatorio")
    @get:NotBlank(message = "El id del 'participante' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'participante' no puede estar vacio")
    val participanteId: Long,

    @get:NotNull(message = "El id de la 'ponencia' es obligatorio")
    @get:NotBlank(message = "El id de la 'ponencia' no puede estar en blanco")
    @get:NotEmpty(message = "El id de la 'ponencia' no puede estar vacio")
    val ponenciaId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    @get:NotBlank(message = "El id del 'congreso' no puede estar en blanco")
    @get:NotEmpty(message = "El id del 'congreso' no puede estar vacio")
    val congresoId: Long,
)
