package com.ucsm.conecta.ucsmconecta.domain.universidad.carrera

import jakarta.persistence.*

@Entity(name = "Escuela_profesional")
@Table(name = "Escuela_profesional")
open class EscuelaProfesional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,

    @Column(nullable = false, length = 255)
    open var nombre: String
) {
    constructor(
        nombre: String
    ) : this(
        null,
        nombre
    )
        override fun toString(): String {
        return "EscuelaProfesional(id=$id, nombre=$nombre)"
    }
}