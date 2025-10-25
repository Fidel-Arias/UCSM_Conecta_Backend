package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.users.colaborador.Colaborador
import com.ucsm.conecta.ucsmconecta.dto.users.auth.colaborador.RegisterColaboradorData
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.ColaboradorBusquedaDTO
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.users.colaborador.ColaboradorRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ColaboradorService @Autowired constructor(
    private val colaboradorRepository: ColaboradorRepository,
    private val escuelaProfesionalService: EscuelaProfesionalService
){
    // Metodo para crear un nuevo colaborador
    @Transactional
    fun createColaborador(@RequestBody @Valid registerColaboradorData: RegisterColaboradorData): Colaborador {
        // Buscar escuela profesional
        val escuelaProfesional: EscuelaProfesional = escuelaProfesionalService.searchEscuelaProfesionalById(registerColaboradorData.escuelaProfesionalId)

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

    // Metodo para buscar colaboradores por apellidos
    fun searchByApellidos(aPaterno: String, aMaterno: String): List<ColaboradorBusquedaDTO> {
        val apellidosCompletos: String = "$aPaterno $aMaterno"
        return colaboradorRepository.findByApellidos(apellidosCompletos)
    }

    // Metodo para buscar colaboradores por nombres
    fun searchByNombres(nombres: String): List<ColaboradorBusquedaDTO> {
        val resultados = colaboradorRepository.findByNombres(nombres)
            .filter { it.estado }
        return resultados
    }

    // Metodo para obtener un colaborador por su ID
    fun getColaboradorById(id: Long): Colaborador = colaboradorRepository.findById(id)
        .orElseThrow { ResourceNotFoundException("Colaborador con id $id no encontrado") }

    // Metodo para obtener todos los colaboradores activos
    fun getAllColaboradores(): List<Colaborador> = colaboradorRepository.findAll()
        .filter { it.estado }

    // Metodo para desactivar un colaborador por su ID
    @Transactional
    fun deactivateColaboradorById(id: Long): Colaborador {
        val colaborador: Colaborador = getColaboradorById(id)
        colaborador.estado = false
        return colaboradorRepository.save(colaborador)
    }

    // Metodo para activar un colaborador por su ID
    @Transactional
    fun activateColaboradorById(id: Long): Colaborador {
        val colaborador: Colaborador = getColaboradorById(id)
        colaborador.estado = true
        return colaboradorRepository.save(colaborador)
    }

    @Transactional
    // Metodo para eliminar un colaborador por su ID
    fun deleteColaboradorById(id: Long) {
        val colaborador: Colaborador = getColaboradorById(id)
        colaboradorRepository.delete(colaborador)
    }

    // Metodo para editar un colaborador
    @Transactional
    fun editColaborador(id: Long, updatedColaboradorData: RegisterColaboradorData): Colaborador {
        val colaborador: Colaborador = getColaboradorById(id)

        val escuelaProfesional: EscuelaProfesional =
            escuelaProfesionalService.searchEscuelaProfesionalById(updatedColaboradorData.escuelaProfesionalId)

        colaborador.apply {
            this.nombres = updatedColaboradorData.nombres
            this.aPaterno = updatedColaboradorData.aPaterno
            this.aMaterno = updatedColaboradorData.aMaterno
            this.escuelaProfesional = escuelaProfesional
        }

        return colaboradorRepository.save(colaborador)
    }
}