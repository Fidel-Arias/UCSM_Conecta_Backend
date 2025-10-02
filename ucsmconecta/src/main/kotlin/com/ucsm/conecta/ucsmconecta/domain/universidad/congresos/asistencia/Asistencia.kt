package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.asistencia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity(name = "Asistencia")
@Table(name = "Asistencia")
open class Asistencia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long?,

    @Column(name = "fecha", nullable = false)
    open var fecha: LocalDate,

    @Column(name = "hora", nullable = false)
    open var hora: LocalTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participante_id", nullable = false)
    open val participante: Participante,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloque_id", nullable = false)
    open val bloque: Bloque,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id", nullable = false)
    open val congreso: Congreso
) {
    constructor(
        fecha: LocalDate,
        hora: LocalTime,
        participante: Participante,
        bloque: Bloque,
        congreso: Congreso
    ) : this(
        id = null,
        fecha = fecha,
        hora = hora,
        participante = participante,
        bloque = bloque,
        congreso = congreso
    )

    override fun toString(): String {
        return "Asistencia(id=$id, fecha=$fecha, hora=$hora, participante=$participante, bloque=$bloque, congreso=$congreso)"
    }
}