package com.ucsm.conecta.ucsmconecta.domain.users.participante

import jakarta.persistence.*

@Entity(name = "TipoParticipante")
@Table(name = "TipoParticipante")
open class TipoParticipante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

    @Column(name = "descripcion", nullable = false)
    open var descripcion: String? = null
) {
    constructor(
        descripcion: String?
    ) : this(
        id = null,
        descripcion = descripcion
    )
}