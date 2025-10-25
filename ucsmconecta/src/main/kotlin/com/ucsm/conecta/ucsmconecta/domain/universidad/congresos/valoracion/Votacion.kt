package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.valoracion

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*

@Entity(name = "Votacion")
@Table(name = "Votacion")
open class Votacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "score", nullable = false)
    open var score: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    open var participante: Participante,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponencia_id", nullable = false)
    open var ponencia: Ponencia,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso
) {
    constructor(
        score: Int,
        participante: Participante,
        ponencia: Ponencia,
        congreso: Congreso
    ) : this(
        id = null,
        score = score,
        participante = participante,
        ponencia = ponencia,
        congreso = congreso
    )
}