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

    @Column(name = "fecha", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    open var fecha: LocalDate = LocalDate.now(),

    @Column(name = "hora", nullable = false, columnDefinition = "TIME DEFAULT CURRENT_TIME")
    open var hora: LocalTime = LocalTime.now(),

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
        participante: Participante,
        bloque: Bloque,
        congreso: Congreso
    ) : this(
        id = null,
        participante = participante,
        bloque = bloque,
        congreso = congreso
    )

    override fun toString(): String {
        return "Asistencia(id=$id, fecha=$fecha, hora=$hora, participante=$participante, bloque=$bloque, congreso=$congreso)"
    }
}