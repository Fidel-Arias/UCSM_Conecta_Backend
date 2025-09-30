package com.ucsm.conecta.ucsmconecta.domain.users.administrador

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import jakarta.persistence.*

@Entity(name = "Congreso_Administrador")
@Table(name = "Congreso_Administrador")
open class CongresoAdministrador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrador_id", nullable = false)
    open var administrador: Administrador,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open var congreso: Congreso
) {
    constructor(
        administrador: Administrador,
        congreso: Congreso
    ) : this(
        id = null,
        administrador = administrador,
        congreso = congreso
    )

    override fun toString(): String {
        return "CongresoAdministrador(id=$id, administrador=${administrador.id}, congreso=${congreso.id})"
    }
}
