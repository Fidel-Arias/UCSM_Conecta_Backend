package com.ucsm.conecta.ucsmconecta.domain.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import jakarta.persistence.*

@Entity(name = "Colaborador")
@Table(name = "Colaborador")
open class Colaborador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null,

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
}