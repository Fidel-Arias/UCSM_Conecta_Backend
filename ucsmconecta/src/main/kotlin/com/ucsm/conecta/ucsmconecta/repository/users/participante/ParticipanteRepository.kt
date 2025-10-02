package com.ucsm.conecta.ucsmconecta.repository.users.participante

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import org.springframework.data.jpa.repository.JpaRepository

interface ParticipanteRepository : JpaRepository<Participante, Long> {
    fun findByNumDocumento(numDocumento: String): Participante?
    fun findByParticipanteId(participanteId: Long): Participante?
    fun findByParticipanteName(nombre: String): List<Participante>
}