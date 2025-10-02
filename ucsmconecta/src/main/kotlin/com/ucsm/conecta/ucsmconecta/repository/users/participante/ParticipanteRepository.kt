package com.ucsm.conecta.ucsmconecta.repository.users.participante

import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ParticipanteRepository : JpaRepository<Participante, Long> {
    fun findByNumDocumento(numDocumento: String): Optional<Participante>
    fun findByName(nombre: String): List<Participante>
    fun findByApellidos(apPaterno: String, apMaterno: String): Optional<Participante>
}