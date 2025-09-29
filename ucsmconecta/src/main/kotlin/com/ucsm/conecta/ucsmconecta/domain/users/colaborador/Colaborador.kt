package com.ucsm.conecta.ucsmconecta.domain.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import jakarta.persistence.*

@Entity(name = "Colaborador")
@Table(name = "Colaborador")
open class Colaborador(
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

}