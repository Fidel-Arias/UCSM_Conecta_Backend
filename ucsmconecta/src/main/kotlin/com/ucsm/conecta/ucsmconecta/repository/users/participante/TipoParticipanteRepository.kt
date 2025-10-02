package com.ucsm.conecta.ucsmconecta.repository.users.participante

import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import org.springframework.data.jpa.repository.JpaRepository

interface TipoParticipanteRepository: JpaRepository<TipoParticipante, Long> {
    fun findByDescripcion(descripcion: String): TipoParticipante?
}