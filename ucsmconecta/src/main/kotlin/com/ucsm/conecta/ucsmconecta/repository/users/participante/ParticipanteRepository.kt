package com.ucsm.conecta.ucsmconecta.repository.users.participante

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.ParticipanteBusquedaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface ParticipanteRepository : JpaRepository<Participante, Long> {
    fun findByNumDocumento(numDocumento: String): Optional<Participante>
    fun findByNombres(nombres: String): List<Participante>

    @Query(
        value = "SELECT * FROM buscar_participante_apellidos(:busqueda)",
        nativeQuery = true
    )
    fun findByApellidos(@Param("busqueda") busqueda: String): List<ParticipanteBusquedaDTO>
}