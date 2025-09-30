package com.ucsm.conecta.ucsmconecta.domain.users.administrador

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import jakarta.persistence.*

@Entity(name = "Administrador")
@Table(name = "Administrador")
open class Administrador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "nombres", nullable = false)
    open var nombres: String? = null,

    @Column(name = "a_paterno", nullable = false)
    open var aPaterno: String? = null,

    @Column(name = "a_materno", nullable = false)
    open var aMaterno: String? = null,

    @Column(name = "email", nullable = false, unique = true)
    open val email: String? = null,

    @Column(name = "password", nullable = false)
    private var password: String? = null,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_profesional_id")
    open var escuelaProfesional: EscuelaProfesional
) {
    constructor(
        nombres: String?,
        aPaterno: String?,
        aMaterno: String?,
        email: String?,
        password: String?,
        estado: Boolean,
        escuelaProfesional: EscuelaProfesional
    ) : this(
        id = null,
        nombres = nombres,
        aPaterno = aPaterno,
        aMaterno = aMaterno,
        email = email,
        password = password,
        estado = estado,
        escuelaProfesional = escuelaProfesional
    )

    override fun toString(): String {
        return "Administrador(id=$id, nombres=$nombres, aPaterno=$aPaterno, aMaterno=$aMaterno, email=$email, password=$password)"
    }

    fun getFullName(): String {
        return "$nombres $aPaterno $aMaterno"
    }
}