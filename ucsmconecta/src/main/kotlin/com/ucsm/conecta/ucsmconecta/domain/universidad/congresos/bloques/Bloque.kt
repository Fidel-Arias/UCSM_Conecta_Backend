package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.dia.Dia
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ubicacion.Ubicacion
import jakarta.persistence.*
import java.time.LocalTime

@Entity(name = "Bloque")
@Table(name = "Bloque")
open class Bloque(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "hora_inicio", nullable = false)
    open var horaInicio: LocalTime? = null,

    @Column(name = "hora_final", nullable = false)
    open var horaFinal: LocalTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dia_d", nullable = false)
    open val dia: Dia,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id", nullable = false)
    open var ubicacion: Ubicacion,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponencia_id", nullable = false)
    open var ponencia: Ponencia
) {
    constructor(
        horaInicio: LocalTime?,
        horaFinal: LocalTime?,
        dia: Dia,
        ubicacion: Ubicacion,
        ponencia: Ponencia
    ) : this(
        id = null,
        horaInicio = horaInicio,
        horaFinal = horaFinal,
        dia = dia,
        ubicacion = ubicacion,
        ponencia = ponencia
    )

    override fun toString(): String {
        return "Bloque(id=$id, horaInicio=$horaInicio, horaFinal=$horaFinal, dia=$dia, ubicacion=$ubicacion, ponencia=$ponencia)"
    }
}