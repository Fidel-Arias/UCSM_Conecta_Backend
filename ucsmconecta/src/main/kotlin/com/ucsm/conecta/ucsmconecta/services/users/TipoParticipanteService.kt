package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.participante.TipoParticipante
import com.ucsm.conecta.ucsmconecta.repository.users.participante.TipoParticipanteRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class TipoParticipanteService @Autowired constructor(
    private val tipoParticipanteRepository: TipoParticipanteRepository
){
    // Metodo para buscar un TipoParticipante por su descripción
    fun searchByDescripcion(descripcion: String): TipoParticipante? = tipoParticipanteRepository.findByDescripcion(descripcion)
        .orElseThrow { EntityNotFoundException("Tipo de Participante no encontrado") }

    // Metodo para buscar un TipoParticipante por su id
    fun searchById(id: Long): TipoParticipante = tipoParticipanteRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Tipo de Participante no encontrado") }

    // Metodo para crear un nuevo TipoParticipante
    @Transactional
    fun createTipoParticipante(@RequestBody @Valid descripcion: String): TipoParticipante {
        val nuevoTipoParticipante = TipoParticipante(
            descripcion = descripcion
        )

        return tipoParticipanteRepository.save(nuevoTipoParticipante)
    }

    // Metodo para obtener todos los Tipos de Participantes
    fun getAllTiposParticipantes(): List<TipoParticipante> = tipoParticipanteRepository.findAll()
}