package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia

import jakarta.validation.constraints.NotNull

data class DataRequestAsistencia(
    @get:NotNull(message = "El id del 'participante' es obligatorio")
    val participanteId: Long,

    @get:NotNull(message = "El id del 'bloque' es obligatorio")
    val bloqueId: Long,

    @get:NotNull(message = "El id del 'congreso' es obligatorio")
    val congresoId: Long
) {
}