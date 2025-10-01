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
    open val id: Long? = null,

    @Column(name = "fecha_inicio", nullable = false)
    open var fechaInicio: LocalDate? = null,

    @Column(name = "fecha_fin", nullable = false)
    open var fechaFin: LocalDate? = null,

    @Column(name = "asistencia_total", nullable = false)
    open var numAsistencias: Int? = null,

    @Column(name = "n_refrigerio", nullable = false)
    open var numRefrigerios: Int? = null,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_profesional_id", nullable = false)
    open var escuelaProfesional: EscuelaProfesional
) {

}