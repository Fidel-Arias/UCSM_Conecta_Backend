package com.ucsm.conecta.ucsmconecta.repository.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.domain.users.colaborador.Colaborador
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.ColaboradorBusquedaDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ColaboradorRepository: JpaRepository<Colaborador, Long> {
    @Query(
        value = "SELECT * FROM buscar_colaborador_apellidos(:busqueda)",
        nativeQuery = true)
    fun findByApellidos(@Param("busqueda") busqueda: String): List<ColaboradorBusquedaDTO>

    @Query(
        value = "SELECT * FROM buscar_colaborador_nombres(:busqueda)",
        nativeQuery = true)
    fun findByNombres(@Param("busqueda") busqueda: String): List<ColaboradorBusquedaDTO>

    fun findAllByEstadoTrueOrderByIdAsc(): List<Colaborador>
}