package com.ucsm.conecta.ucsmconecta.domain.users.administrador

import jakarta.persistence.*

@Entity(name = "Administrador")
@Table(name = "Administrador")
open class Administrador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(nullable = false)
    open var nombres: String? = null,

    @Column(nullable = false)
    open var aPaterno: String? = null,

    @Column(nullable = false)
    open var aMaterno: String? = null,

    @Column(nullable = false, unique = true)
    open val email: String? = null,

    @Column(nullable = false)
    open var password: String? = null
) {
    constructor(nombres: String?, aPaterno: String?, aMaterno: String?, email: String?, password: String?) : this(
        null,
        nombres,
        aPaterno,
        aMaterno,
        email,
        password
    )

    override fun toString(): String {
        return "Administrador(id=$id, nombres=$nombres, aPaterno=$aPaterno, aMaterno=$aMaterno, email=$email, password=$password)"
    }

    fun getFullName(): String {
        return "$nombres $aPaterno $aMaterno"
    }
}