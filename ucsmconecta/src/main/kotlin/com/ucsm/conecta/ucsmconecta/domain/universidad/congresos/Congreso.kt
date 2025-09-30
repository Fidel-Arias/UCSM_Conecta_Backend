package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import jakarta.persistence.*

@Entity(name = "Congreso")
@Table(name = "Congreso")
open class Congreso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "fecha_inicio", nullable = false)
    open var fechaInicio: String? = null,

    @Column(name = "fecha_fin", nullable = false)
    open var fechaFin: String? = null,

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