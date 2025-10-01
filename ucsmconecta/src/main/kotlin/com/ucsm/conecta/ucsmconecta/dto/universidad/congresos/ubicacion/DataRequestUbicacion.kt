package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ubicacion

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class DataRequestUbicacion(
    @get:NotNull(message = "El campo 'nombre' es obligatorio" )
    @get:NotBlank(message = "El campo 'nombre' no puede estar en blanco" )
    @get:NotEmpty(message = "El campo 'nombre' no puede estar vacio" )
    var nombre: String,

    @get:NotBlank(message = "El campo 'estado' es obligatorio" )
    @get:NotBlank(message = "El campo 'estado' no puede estar en blanco" )
    @get:NotEmpty(message = "El campo 'estado' no puede estar vacio" )
    var estado: Boolean,
){}
