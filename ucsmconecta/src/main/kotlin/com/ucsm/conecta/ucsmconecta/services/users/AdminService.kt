package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import com.ucsm.conecta.ucsmconecta.dto.users.auth.admin.RegisterAdminData
import com.ucsm.conecta.ucsmconecta.dto.users.auth.admin.UpdateDataAdministrador
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.users.admin.AdminRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class AdminService @Autowired constructor(
    private val adminRepository: AdminRepository,
    private val escuelaProfesionalService: EscuelaProfesionalService
) {
    // Metodo para crear un administrador
    @Transactional
    fun createAdmin(@RequestBody @Valid registerAdminData: RegisterAdminData): Administrador {
        // Buscar escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional = escuelaProfesionalService.searchEscuelaProfesionalById(registerAdminData.escuelaProfesionalId)

        return adminRepository.save(Administrador(
                nombres = registerAdminData.nombres,
                aPaterno = registerAdminData.aPaterno,
                aMaterno = registerAdminData.aMaterno,
                email = registerAdminData.email,
                password = registerAdminData.password,
                escuelaProfesional = escuelaProfesional
            )
        )
    }

    // Metodo para buscar un administrador por su id
    fun getAdminById(id: Long, includeInactive: Boolean = false): Administrador {
        val admin = adminRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Administrador con id $id no encontrado") }

        if (!admin.estado && !includeInactive)
            throw ResourceNotFoundException("Administrador con id $id está desactivado o no disponible")

        return admin
    }

    // Metodo para desactivar un administrador por su id
    @Transactional
    fun deactivateAdminById(id: Long) {
        val admin: Administrador = getAdminById(id)
        admin.estado = false
        adminRepository.save(admin)
    }

    // Metodo para activar un administrador por su id
    @Transactional
    fun activateAdminById(id: Long) {
        val admin: Administrador = getAdminById(id, includeInactive = true)

        if (admin.estado) {
            throw IllegalStateException("El administrador ya está activo")
        }

        admin.estado = true
        adminRepository.save(admin)
    }

    // Metodo para eliminar un administrador por su id
    @Transactional
    fun deleteAdminById(id: Long) {
        val admin: Administrador = getAdminById(id)
        adminRepository.delete(admin)
    }

    @Transactional
    fun updateAdministrador(id: Long, @RequestBody @Valid updateDataAdministrador: UpdateDataAdministrador): Administrador {
        val admin: Administrador = getAdminById(id)

        // Solo actualiza si los campos no son nulos o vacíos
        updateDataAdministrador.nombres.takeIf { !it.isNullOrBlank() }?.let {
            admin.nombres = it
        }

        updateDataAdministrador.aPaterno.takeIf { !it.isNullOrBlank() }?.let {
            admin.aPaterno = it
        }

        updateDataAdministrador.aMaterno.takeIf { !it.isNullOrBlank() }?.let {
            admin.aMaterno = it
        }

        updateDataAdministrador.password.takeIf { !it.isNullOrBlank() }?.let {
            admin.changePassword(it)
        }

        return adminRepository.save(admin)
    }
}