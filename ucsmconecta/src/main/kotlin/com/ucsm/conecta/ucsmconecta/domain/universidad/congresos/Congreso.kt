package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "Congreso")
@Table(name = "Congreso")
open class Congreso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "nombre", nullable = false, length = 255)
    open var nombre: String,

    @Column(name = "fecha_inicio", nullable = false)
    open var fechaInicio: LocalDate,

    @Column(name = "fecha_fin", nullable = false)
    open var fechaFin: LocalDate,

    @Column(name = "asistencia_total", nullable = false)
    open var numAsistencias: Int,

    @Column(name = "n_refrigerio", nullable = false)
    open var numRefrigerios: Int,

    @Column(name = "estado", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    open var estado: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_profesional_id", nullable = false)
    open var escuelaProfesional: EscuelaProfesional
) {
    constructor(
        nombre: String,
        fechaInicio: LocalDate,
        fechaFin: LocalDate,
        numAsistencias: Int,
        numRefrigerios: Int,
        escuelaProfesional: EscuelaProfesional
    ) : this(
        id = null,
        nombre = nombre,
        fechaInicio = fechaInicio,
        fechaFin = fechaFin,
        numAsistencias = numAsistencias,
        numRefrigerios = numRefrigerios,
        estado = true,
        escuelaProfesional = escuelaProfesional
    )

    override fun toString(): String {
        return "Congreso(id=$id, nombre='$nombre', fechaInicio=$fechaInicio, fechaFin=$fechaFin, numAsistencias=$numAsistencias, numRefrigerios=$numRefrigerios, estado=$estado, escuelaProfesional=${escuelaProfesional.id})"
    }
}