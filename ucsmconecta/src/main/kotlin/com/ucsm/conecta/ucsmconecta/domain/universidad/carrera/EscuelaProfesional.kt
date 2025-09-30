package com.ucsm.conecta.ucsmconecta.domain.universidad.carrera

import jakarta.persistence.*

@Entity(name = "Escuela_profesional")
@Table(name = "Escuela_profesional")
open class EscuelaProfesional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    @Column(nullable = false)
    open var nombre: String? = null
) {
    constructor(
        nombre: String?
    ) : this(
        null,
        nombre
    )
        override fun toString(): String {
        return "EscuelaProfesional(id=$id, nombre=$nombre)"
    }
}