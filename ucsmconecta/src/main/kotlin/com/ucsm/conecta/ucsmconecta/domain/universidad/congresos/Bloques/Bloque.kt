package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Bloques

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Dia.Dia
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Ubicacion.Ubicacion
import jakarta.persistence.*

@Entity(name = "Bloque")
@Table(name = "Bloque")
open class Bloque(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "hora_inicio", nullable = false)
    open var horaInicio: String? = null,

    @Column(name = "hora_final", nullable = false)
    open var horaFinal: String? = null,

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
        horaInicio: String?,
        horaFinal: String?,
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