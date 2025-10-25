package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.valoracion

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity(name = "Comentario")
@Table(name = "Comentario")
open class Comentario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "texto", nullable = false)
    open var texto: String,

    @Column(name = "fecha", nullable = false)
    open var fecha: LocalDate,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    open var participante: Participante
) {
    constructor(
        texto: String,
        fecha: LocalDate,
        participante: Participante
    ) : this(
        id = null,
        texto = texto,
        fecha = fecha,
        estado = true,
        participante = participante
    )

    override fun toString(): String {
        return "Comentario(id=$id, texto=$texto, fecha=$fecha, estado=$estado, participante=$participante)"
    }
}