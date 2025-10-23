package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.users.colaborador.Colaborador
import com.ucsm.conecta.ucsmconecta.dto.users.auth.colaborador.RegisterColaboradorData
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.ColaboradorBusquedaDTO
import com.ucsm.conecta.ucsmconecta.repository.users.colaborador.ColaboradorRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ColaboradorService @Autowired constructor(
    private val colaboradorRepository: ColaboradorRepository,
    private val escuelaProfesionalService: EscuelaProfesionalService
){
    fun createColaborador(@RequestBody @Valid registerColaboradorData: RegisterColaboradorData): Colaborador {
        // Buscar escuela profesional
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.getEscuelaProfesionalById(registerColaboradorData.escuelaProfesionalId)

        // Crear y guardar el colaborador
        return colaboradorRepository.save(Colaborador(
            nombres = registerColaboradorData.nombres,
            aPaterno = registerColaboradorData.aPaterno,
            aMaterno = registerColaboradorData.aMaterno,
            email = registerColaboradorData.email,
            password = registerColaboradorData.password,
            escuelaProfesional = escuelaProfesional
        ))
    }

    fun searchByApellidos(aPaterno: String, aMaterno: String): List<ColaboradorBusquedaDTO> {
        val apellidosCompletos: String = "$aPaterno $aMaterno"
        val resultados = colaboradorRepository.findByApellidos(apellidosCompletos)

        if (resultados.isEmpty()) {
            throw EntityNotFoundException("No se encontraron colaboradores con los apellidos proporcionados")
        }
        return resultados
    }

    fun searchByNombres(nombres: String): List<ColaboradorBusquedaDTO> {
        val resultados = colaboradorRepository.findByNombres(nombres)

        if (resultados.isEmpty()) {
            throw EntityNotFoundException("No se encontraron colaboradores con los nombres proporcionados")
        }
        return resultados
    }

    fun getColaboradorById(id: Long): Colaborador? = colaboradorRepository.findById(id)
        .orElseThrow { RuntimeException("Colaborador no encontrado por su ID") }

    fun getAllColaboradores(): List<Colaborador> = colaboradorRepository.findAll()
}