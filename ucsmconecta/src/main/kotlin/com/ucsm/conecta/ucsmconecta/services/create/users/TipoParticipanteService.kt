package com.ucsm.conecta.ucsmconecta.services.create.users

import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.repository.users.participante.TipoParticipanteRepository
import org.springframework.beans.factory.annotation.Autowired

class TipoParticipanteService @Autowired constructor(
    private val tipoParticipanteRepository: TipoParticipanteRepository
){
    fun searchByDescripcion(descripcion: String): TipoParticipante {
        return tipoParticipanteRepository.findByDescripcion(descripcion)
            ?: throw RuntimeException("Tipo de Participante no encontrado")
    }

    fun searchById(id: Long): TipoParticipante = tipoParticipanteRepository.findById(id)
        .orElseThrow { RuntimeException("Tipo de Participante no encontrado") }
}