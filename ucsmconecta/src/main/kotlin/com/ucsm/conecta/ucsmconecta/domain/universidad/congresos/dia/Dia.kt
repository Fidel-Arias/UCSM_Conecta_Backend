package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "Dia")
@Table(name = "Dia")
open class Dia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "fecha", nullable = false)
    open var fecha: LocalDate,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso
){
    constructor(
        fecha: LocalDate,
        congreso: Congreso
    ) : this(
        id = null,
        fecha = fecha,
        estado = true,
        congreso = congreso
    )

    override fun toString(): String {
        return "Dia(id=$id, fecha=$fecha, estado=$estado, congreso=$congreso)"
    }
}