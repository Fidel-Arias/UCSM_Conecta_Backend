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
    open val id: Long?,

    @Column(nullable = false, length = 40)
    open var nombres: String,

    @Column(nullable = false, length = 25)
    open var apPaterno: String,

    @Column(nullable = false, length = 25)
    open var apMaterno: String,

    @Column(nullable = false, unique = true, length = 20)
    open val numDocumento: String,

    @Column(nullable = false, unique = true, length = 255)
    open val email: String,

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

    @Column(nullable = false, length = 15)
    open var estado: String,

    @Column(nullable = true, length = 255)
    open var qr_code: String? = null,
) {
    constructor(
        nombres: String,
        apPaterno: String,
        apMaterno: String,
        numDocumento: String,
        email: String,
        tipoParticipante: TipoParticipante,
        escuelaProfesional: EscuelaProfesional,
        congreso: Congreso,
        estado: String,
        qr_code: String?
    ) : this(
        null,
        nombres,
        apPaterno,
        apMaterno,
        numDocumento,
        email,
        tipoParticipante,
        escuelaProfesional,
        congreso,
        estado,
        qr_code
    )
    override fun toString(): String {
        return "Participante(id=$id, nombres=$nombres, aPaterno=$apPaterno, aMaterno=$apMaterno, n_documento=$numDocumento, email=$email, tipoParticipante=$tipoParticipante, escuelaProfesional=$escuelaProfesional, congreso=$congreso, estado=$estado, qr_code=$qr_code)"
    }
    fun getFullName(): String {
        return "$nombres $apPaterno $apMaterno"
    }
}