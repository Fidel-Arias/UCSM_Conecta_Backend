package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ubicacion

import jakarta.persistence.*

@Entity(name = "Ubicacion")
@Table(name = "Ubicacion")
open class Ubicacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "nombre", nullable = false)
    open var nombre: String? = null,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean = true
) {
    constructor(
        nombre: String?,
        estado: Boolean
    ) : this(
        id = null,
        nombre = nombre,
        estado = estado
    )

    override fun toString(): String {
        return "Ubicacion(id=$id, nombre=$nombre, estado=$estado)"
    }
}