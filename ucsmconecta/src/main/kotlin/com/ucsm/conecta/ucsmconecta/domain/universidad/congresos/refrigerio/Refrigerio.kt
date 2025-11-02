package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity(name = "Refrigerio")
@Table(name = "Refrigerio")
open class Refrigerio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "fecha", nullable = false)
    open var fecha: LocalDate = LocalDate.now(),

    @Column(name = "hora", nullable = false)
    open val hora: LocalTime = LocalTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    open val participante: Participante,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso
) {
    constructor(
        participante: Participante,
        congreso: Congreso
    ) : this(
        id = null,
        participante = participante,
        congreso = congreso
    )
}