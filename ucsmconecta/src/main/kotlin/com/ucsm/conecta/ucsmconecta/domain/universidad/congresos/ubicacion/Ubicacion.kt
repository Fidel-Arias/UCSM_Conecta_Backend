package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ubicacion

import jakarta.persistence.*

@Entity(name = "Ubicacion")
@Table(name = "Ubicacion")
open class Ubicacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "nombre", nullable = false)
    open var nombre: String,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean
) {
    constructor(
        nombre: String,
    ) : this(
        id = null,
        nombre = nombre,
        estado = true
    )

    override fun toString(): String {
        return "Ubicacion(id=$id, nombre=$nombre, estado=$estado)"
    }
}