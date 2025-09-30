package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Valoracion

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*

@Entity(name = "Votacion")
@Table(name = "Votacion")
open class Votacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "score", nullable = false)
    open var score: Int? = null,

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
}