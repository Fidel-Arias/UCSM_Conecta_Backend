package com.ucsm.conecta.ucsmconecta.domain.universidad.carrera

import jakarta.persistence.*

@Entity(name = "Escuela_profesional")
@Table(name = "Escuela_profesional")
open class EscuelaProfesional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,

    @Column(nullable = false)
    open var nombre: String,

    @Column(nullable = false)
    open var codigo: String
) {
    constructor(
        nombre: String,
        codigo: String
    ) : this(
        null,
        nombre,
        codigo
    )
}