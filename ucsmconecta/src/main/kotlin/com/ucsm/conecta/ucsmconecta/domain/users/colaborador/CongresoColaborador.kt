package com.ucsm.conecta.ucsmconecta.domain.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import jakarta.persistence.*

@Entity(name = "Congreso_Colaborador")
@Table(name = "Congreso_Colaborador")
open class CongresoColaborador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id", nullable = false)
    open var colaborador: Colaborador,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open var congreso: Congreso
) {
    constructor(
        colaborador: Colaborador,
        congreso: Congreso
    ) : this(
        id = null,
        colaborador = colaborador,
        congreso = congreso
    )

    override fun toString(): String {
        return "CongresoColaborador(id=$id, colaborador=${colaborador.id}, congreso=${congreso.id})"
    }
}