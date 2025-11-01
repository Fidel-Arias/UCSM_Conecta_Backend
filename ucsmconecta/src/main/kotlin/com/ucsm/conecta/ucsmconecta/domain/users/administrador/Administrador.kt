package com.ucsm.conecta.ucsmconecta.domain.users.administrador

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import jakarta.persistence.*

@Entity(name = "Administrador")
@Table(name = "Administrador")
open class Administrador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "nombres", nullable = false, length = 40)
    open var nombres: String,

    @Column(name = "a_paterno", nullable = false, length = 25)
    open var aPaterno: String,

    @Column(name = "a_materno", nullable = false, length = 25)
    open var aMaterno: String,

    @Column(name = "email", nullable = false, unique = true, length = 255)
    open val email: String,

    @Column(name = "password", nullable = false)
    private var password: String,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_profesional_id")
    open val escuelaProfesional: EscuelaProfesional
) {
    constructor(
        nombres: String,
        aPaterno: String,
        aMaterno: String,
        email: String,
        password: String,
        escuelaProfesional: EscuelaProfesional
    ) : this(
        null,
        nombres,
        aPaterno,
        aMaterno,
        email,
        password,
        estado = true,
        escuelaProfesional
    )

    override fun toString(): String {
        return "Administrador(id=$id, nombres=$nombres, aPaterno=$aPaterno, aMaterno=$aMaterno, email=$email, password=$password)"
    }

    fun getFullName(): String {
        return "$nombres $aPaterno $aMaterno"
    }
    fun changePassword(newPassword: String) {
        this.password = newPassword
    }
}