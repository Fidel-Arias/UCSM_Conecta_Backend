package com.ucsm.conecta.ucsmconecta.domain.universidad.gradoacademico

import jakarta.persistence.*

@Entity(name = "Grado_academico")
@Table(name = "Grado_academico")
open class GradoAcademico(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "descripcion", nullable = false, length = 20)
    open var descripcion: String
) {
    constructor(
        descripcion: String
    ) : this(
        id = null,
        descripcion = descripcion
    )

    override fun toString(): String {
        return "GradoAcademico(id=$id, descripcion='$descripcion')"
    }
}