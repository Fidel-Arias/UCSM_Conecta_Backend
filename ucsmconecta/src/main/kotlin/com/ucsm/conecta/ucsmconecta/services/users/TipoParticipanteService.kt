package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.repository.users.participante.TipoParticipanteRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class TipoParticipanteService @Autowired constructor(
    private val tipoParticipanteRepository: TipoParticipanteRepository
){
    fun searchByDescripcion(descripcion: String): TipoParticipante? = tipoParticipanteRepository.findByDescripcion(descripcion)
        .orElseThrow { EntityNotFoundException("Tipo de Participante no encontrado") }

    fun searchById(id: Long): TipoParticipante? = tipoParticipanteRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Tipo de Participante no encontrado") }

    fun createTipoParticipante(@RequestBody @Valid descripcion: String): TipoParticipante? {
        val nuevoTipoParticipante = TipoParticipante(
            descripcion = descripcion
        )

        return tipoParticipanteRepository.save(nuevoTipoParticipante)
    }

    fun getAllTiposParticipantes(): List<TipoParticipante> = tipoParticipanteRepository.findAll()
}