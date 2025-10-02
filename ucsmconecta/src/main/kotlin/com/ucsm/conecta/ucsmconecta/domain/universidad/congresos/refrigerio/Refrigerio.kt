package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "Refrigerio")
@Table(name = "Refrigerio")
open class Refrigerio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "fecha", nullable = false)
    open var fecha: LocalDate,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    open val participante: Participante,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso
) {
    constructor(
        fecha: LocalDate,
        estado: Boolean,
        participante: Participante,
        congreso: Congreso
    ) : this(
        id = null,
        fecha = fecha,
        estado = estado,
        participante = participante,
        congreso = congreso
    )
}