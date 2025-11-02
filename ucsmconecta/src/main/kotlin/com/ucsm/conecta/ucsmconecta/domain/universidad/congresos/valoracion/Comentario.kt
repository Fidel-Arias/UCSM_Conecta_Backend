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
    val id: Long?,

    @Column(name = "texto", nullable = false)
    var texto: String,

    @Column(name = "fecha", nullable = false)
    val fecha: LocalDate = LocalDate.now(),

    @Column(name = "hora", nullable = false)
    val hora: LocalTime = LocalTime.now(),

    @Column(name = "estado", nullable = false)
    var estado: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    var participante: Participante
) {
    constructor(
        texto: String,
        estado: Boolean,
        participante: Participante
    ) : this(
        id = null,
        texto = texto,
        estado = estado,
        participante = participante
    )

    override fun toString(): String {
        return "Comentario(id=$id, texto=$texto, fecha=$fecha, estado=$estado, participante=$participante)"
    }
}