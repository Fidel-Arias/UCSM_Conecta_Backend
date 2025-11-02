package com.ucsm.conecta.ucsmconecta.domain.users.ponente

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.universidad.gradoacademico.GradoAcademico
import jakarta.persistence.*

@Entity(name = "Ponente")
@Table(name = "Ponente")
open class Ponente (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(nullable = false, length = 40)
    open var nombres: String,

    @Column(nullable = false, length = 40)
    open var apellidos: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grado_academico_id")
    var gradoAcademico: GradoAcademico,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id")
    val congreso: Congreso
){
    constructor(
        nombres: String,
        apellidos: String,
        gradoAcademico: GradoAcademico,
        congreso: Congreso
    ) : this(
        null,
        nombres,
        apellidos,
        gradoAcademico,
        congreso
    )

    override fun toString(): String {
        return "Ponente(id=$id, nombres='$nombres', apellidos='$apellidos', gradoAcademico=$gradoAcademico, congreso=$congreso)"
    }

}