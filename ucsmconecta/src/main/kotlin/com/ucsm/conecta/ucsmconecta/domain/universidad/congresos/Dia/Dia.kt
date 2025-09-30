package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Dia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import jakarta.persistence.*

@Entity(name = "Dia")
@Table(name = "Dia")
open class Dia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "fecha", nullable = false)
    open var fecha: String? = null,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso
){
    constructor(
        fecha: String?,
        estado: Boolean,
        congreso: Congreso
    ) : this(
        id = null,
        fecha = fecha,
        estado = estado,
        congreso = congreso
    )

    override fun toString(): String {
        return "Dia(id=$id, fecha=$fecha, estado=$estado, congreso=$congreso)"
    }
}