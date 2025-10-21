package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.repository.users.participante.TipoParticipanteRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TipoParticipanteService @Autowired constructor(
    private val tipoParticipanteRepository: TipoParticipanteRepository
){
    fun searchByDescripcion(descripcion: String): TipoParticipante? = tipoParticipanteRepository.findByDescripcion(descripcion)
        .orElseThrow { EntityNotFoundException("Tipo de Participante no encontrado") }

    fun searchById(id: Long): TipoParticipante? = tipoParticipanteRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Tipo de Participante no encontrado") }
}