package com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Asistencia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Bloques.Bloque
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import jakarta.persistence.*

@Entity(name = "Asistencia")
@Table(name = "Asistencia")
open class Asistencia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(name = "fecha", nullable = false)
    open var fecha: String? = null,

    @Column(name = "hora", nullable = false)
    open var hora: String? = null,

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
        fecha: String?,
        hora: String?,
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