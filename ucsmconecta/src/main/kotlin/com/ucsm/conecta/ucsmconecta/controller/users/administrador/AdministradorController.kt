package com.ucsm.conecta.ucsmconecta.controller.users.administrador

import com.ucsm.conecta.ucsmconecta.domain.users.administrador.Administrador
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.users.auth.admin.RegisterAdminData
import com.ucsm.conecta.ucsmconecta.dto.users.auth.admin.UpdateDataAdministrador
import com.ucsm.conecta.ucsmconecta.dto.users.profile.admin.DataResponseAdmin
import com.ucsm.conecta.ucsmconecta.services.users.AdminService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/administradores")
class AdministradorController @Autowired constructor(
    private val adminService: AdminService,
) {
    // Metodo para crear un nuevo administrador
    @PostMapping
    fun createAdministrador(@RequestBody @Valid registerAdminData: RegisterAdminData, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseAdmin> {
        // Crear el administrador
        val admin: Administrador = adminService.createAdmin(registerAdminData)

        // Se pasan los datos creados a DataResponseAdmin para visualizarlos
        val dataResponseAdmin = DataResponseAdmin(
            id = admin.id!!,
            nombres =  admin.nombres,
            aPaterno = admin.aPaterno,
            aMaterno = admin.aMaterno,
            estado = admin.estado,
            escuelaProfesional = DataResponseEscuelaProfesional(
                id = admin.escuelaProfesional.id!!,
                nombre = admin.escuelaProfesional.nombre
            )
        )

        // Crear la URI para el nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/administradores/{id}")
            .buildAndExpand(admin.id).toUri()

        return ResponseEntity.created(uri).body(dataResponseAdmin)
    }

    // Metodo para obtener un administrador por su ID
    @GetMapping("/{id}")
    fun getAdministradorById(@PathVariable id: Long): ResponseEntity<DataResponseAdmin> {
        val admin: Administrador = adminService.getAdminById(id)

        if (!admin.estado) {
            return ResponseEntity.notFound().build()
        }

        val dataResponseAdmin = DataResponseAdmin(
            id = admin.id!!,
            nombres =  admin.nombres,
            aPaterno = admin.aPaterno,
            aMaterno = admin.aMaterno,
            estado = admin.estado,
            escuelaProfesional = DataResponseEscuelaProfesional(
                id = admin.escuelaProfesional.id!!,
                nombre = admin.escuelaProfesional.nombre
            )
        )

        return ResponseEntity.ok(dataResponseAdmin)
    }

    // Metodo para desactivar un administrador por su ID
    @DeleteMapping("/deactivate/{id}")
    fun deleteAdministrador(@PathVariable id: Long): ResponseEntity<Void> {
        adminService.deactivateAdminById(id)
        return ResponseEntity.noContent().build()
    }

    // Endpoint para activar un administrador por ID
    @PutMapping("/activate/{id}")
    fun activateAdministrador(@PathVariable id: Long): ResponseEntity<Void> {
        adminService.activateAdminById(id)
        return ResponseEntity.noContent().build()
    }

    // Endpoint para actualizar el usuario
    @PutMapping("/{id}")
    fun updateAdministrador(@PathVariable id: Long, @RequestBody @Valid updateDataAdministrador: UpdateDataAdministrador): ResponseEntity<Void> {
        adminService.updateAdministrador(id, updateDataAdministrador)
        return ResponseEntity.noContent().build()
    }
}