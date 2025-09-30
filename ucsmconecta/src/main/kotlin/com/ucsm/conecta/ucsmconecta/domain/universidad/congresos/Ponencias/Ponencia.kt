package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Ponencias

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.ponente.Ponente
import jakarta.persistence.*

@Entity(name = "Ponencia")
@Table(name = "Ponencia")
open class Ponencia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "nombre", nullable = false)
    open var nombre: String? = null,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponente_id")
    open var ponente: Ponente,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso

) {
    constructor(
        nombre: String?,
        estado: Boolean,
        ponente: Ponente,
        congreso: Congreso
    ) : this(
        id = null,
        nombre = nombre,
        estado = estado,
        ponente = ponente,
        congreso = congreso
    )

    override fun toString(): String {
        return "Ponencia(id=$id, nombre=$nombre, estado=$estado, ponente=$ponente, congreso=$congreso)"
    }
}