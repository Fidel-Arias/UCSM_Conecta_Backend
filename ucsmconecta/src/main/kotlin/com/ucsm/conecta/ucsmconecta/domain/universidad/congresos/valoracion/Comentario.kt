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
    open val id: Long? = null,

    @Column(name = "texto", nullable = false)
    open var texto: String? = null,

    @Column(name = "fecha", nullable = false)
    open var fecha: LocalDate? = null,

    @Column(name = "estado", nullable = false)
    open var estado: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    open var participante: Participante
) {
    constructor(
        texto: String?,
        fecha: LocalDate?,
        participante: Participante
    ) : this(
        id = null,
        texto = texto,
        fecha = fecha,
        participante = participante
    )

    override fun toString(): String {
        return "Comentario(id=$id, texto=$texto, fecha=$fecha, estado=$estado, participante=$participante)"
    }
}