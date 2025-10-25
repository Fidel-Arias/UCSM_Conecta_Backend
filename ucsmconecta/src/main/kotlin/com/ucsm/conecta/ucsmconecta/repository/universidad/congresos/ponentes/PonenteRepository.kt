package com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.ponentes

import com.ucsm.conecta.ucsmconecta.domain.users.ponente.Ponente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PonenteRepository: JpaRepository<Ponente, Long> {
    fun findByNombres(nombres: String): Optional<Ponente>
}