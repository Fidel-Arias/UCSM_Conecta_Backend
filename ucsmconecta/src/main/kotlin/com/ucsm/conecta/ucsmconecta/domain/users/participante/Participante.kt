package com.ucsm.conecta.ucsmconecta.domain.users.participante

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import jakarta.persistence.*

@Entity(name = "Participante")
@Table(name = "Participante")
open class Participante(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open val id: Long? = null,

    @Column(nullable = false)
    open var nombres: String? = null,

    @Column(nullable = false)
    open var aPaterno: String? = null,

    @Column(nullable = false)
    open var aMaterno: String? = null,

    @Column(nullable = false, unique = true)
    open val n_documento: String? = null,

    @Column(nullable = false, unique = true)
    open val email: String? = null,

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_participante_id")
    open val tipoParticipante: TipoParticipante,

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_profesional_id")
    open var escuelaProfesional: EscuelaProfesional,

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "congreso_id")
    open val congreso: Congreso,

    @Column(nullable = false)
    open var estado: String? = null,

    @Column(nullable = true)
    open var qr_code: String? = null,
) {
    constructor(
        nombres: String?,
        aPaterno: String?,
        aMaterno: String?,
        n_documento: String?,
        email: String?,
        tipoParticipante: TipoParticipante,
        escuelaProfesional: EscuelaProfesional,
        congreso: Congreso,
        estado: String?,
        qr_code: String?
    ) : this(
        null,
        nombres,
        aPaterno,
        aMaterno,
        n_documento,
        email,
        tipoParticipante,
        escuelaProfesional,
        congreso,
        estado,
        qr_code
    )
    override fun toString(): String {
        return "Participante(id=$id, nombres=$nombres, aPaterno=$aPaterno, aMaterno=$aMaterno, n_documento=$n_documento, email=$email, tipoParticipante=$tipoParticipante, escuelaProfesional=$escuelaProfesional, congreso=$congreso, estado=$estado, qr_code=$qr_code)"
    }
    fun getFullName(): String {
        return "$nombres $aPaterno $aMaterno"
    }
}