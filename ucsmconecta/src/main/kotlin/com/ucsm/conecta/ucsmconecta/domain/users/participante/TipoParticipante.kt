package com.ucsm.conecta.ucsmconecta.domain.users.participante

import jakarta.persistence.*

@Entity(name = "Tipo_participante")
@Table(name = "Tipo_participante")
open class TipoParticipante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long?,

    @Column(name = "descripcion", nullable = false, length = 30)
    open var descripcion: String
) {
    constructor(
        descripcion: String
    ) : this(
        id = null,
        descripcion = descripcion
    )
}