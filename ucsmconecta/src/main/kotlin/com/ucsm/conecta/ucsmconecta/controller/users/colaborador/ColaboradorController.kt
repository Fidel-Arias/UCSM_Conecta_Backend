package com.ucsm.conecta.ucsmconecta.controller.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.users.colaborador.Colaborador
import com.ucsm.conecta.ucsmconecta.dto.universidad.carrera.DataResponseEscuelaProfesional
import com.ucsm.conecta.ucsmconecta.dto.users.auth.colaborador.RegisterColaboradorData
import com.ucsm.conecta.ucsmconecta.dto.users.auth.colaborador.UpdateDataColaborador
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.ColaboradorBusquedaDTO
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.DataResponseColaborador
import com.ucsm.conecta.ucsmconecta.services.users.ColaboradorService
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/colaboradores")
class ColaboradorController @Autowired constructor(
    private val colaboradorService: ColaboradorService,
){
    @PostMapping
    fun createColaborador(@RequestBody @Valid registerColaboradorData: RegisterColaboradorData, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseColaborador> {
        // Crear el colaborador
        val colaborador: Colaborador = colaboradorService.createColaborador(registerColaboradorData)

        // Se pasan los datos creados a DataResponseColaborador para visualizarlos
        val dataResponseColaborador = DataResponseColaborador(
            id = colaborador.id!!,
            nombres = colaborador.nombres,
            aPaterno = colaborador.aPaterno,
            aMaterno = colaborador.aMaterno,
            email = colaborador.email,
            estado = colaborador.estado,
            escuelaProfesional = DataResponseEscuelaProfesional(
                id = colaborador.escuelaProfesional.id!!,
                nombre = colaborador.escuelaProfesional.nombre,
            )
        )
        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/colaboradores/{id}")
            .buildAndExpand(colaborador.id).toUri()

        return ResponseEntity.created(uri).body(dataResponseColaborador)
    }

    @GetMapping("/{id}")
    fun getColaboradorById(@PathVariable id: Long): ResponseEntity<DataResponseColaborador> {
        val colaborador: Colaborador = colaboradorService.getColaboradorById(id)

        if (!colaborador.estado) {
            return ResponseEntity.noContent().build()
        }

        val dataResponseColaborador: DataResponseColaborador = DataResponseColaborador(
            id = colaborador.id!!,
            nombres = colaborador.nombres,
            aPaterno = colaborador.aPaterno,
            aMaterno = colaborador.aMaterno,
            email = colaborador.email,
            estado = colaborador.estado,
            escuelaProfesional = DataResponseEscuelaProfesional(
                id = colaborador.escuelaProfesional.id!!,
                nombre = colaborador.escuelaProfesional.nombre,
            )
        )

        return ResponseEntity.ok(dataResponseColaborador)
    }

    @GetMapping
    fun getAllColaboradores(): ResponseEntity<List<DataResponseColaborador>> {
        val colaboradores: List<Colaborador> = colaboradorService.getAllColaboradores()

        if (colaboradores.isEmpty()) {
            return ResponseEntity.noContent().build()
        }

        val dataResponseColaborador: List<DataResponseColaborador> = colaboradores.map { colaborador ->
            DataResponseColaborador(
                id = colaborador.id!!,
                nombres = colaborador.nombres,
                aPaterno = colaborador.aPaterno,
                aMaterno = colaborador.aMaterno,
                email = colaborador.email,
                estado = colaborador.estado,
                escuelaProfesional = DataResponseEscuelaProfesional(
                    id = colaborador.escuelaProfesional.id!!,
                    nombre = colaborador.escuelaProfesional.nombre,
                )
            )
        }

        return ResponseEntity.ok(dataResponseColaborador)
    }

    @GetMapping("/search/nombres")
    fun searchColaboradoresByNombres(@RequestParam nombres: String): ResponseEntity<List<ColaboradorBusquedaDTO>> {
        // Buscar colaboradores por nombres
        val colaboradores: List<ColaboradorBusquedaDTO> = colaboradorService.searchByNombres(nombres)

        return if (colaboradores.isEmpty()) {
            ResponseEntity.noContent().build() // 204 No Content
        } else {
            ResponseEntity.ok(colaboradores) // 200 OK con la lista de colaboradores
        }
    }

    @GetMapping("/search/apellidos")
    fun searchColaboradoresByApellidos(
        @RequestParam(required = false) aPaterno: String?,
        @RequestParam(required = false) aMaterno: String?
    ): ResponseEntity<List<ColaboradorBusquedaDTO>> {
        // Buscar colaboradores por apellidos
        val colaboradores: List<ColaboradorBusquedaDTO> = colaboradorService.searchByApellidos(aPaterno, aMaterno)

        return if (colaboradores.isEmpty()) {
            ResponseEntity.noContent().build() // 204 No Content
        } else {
            ResponseEntity.ok(colaboradores) // 200 OK con la lista de colaboradores
        }
    }

    // Metodo para desactivar un colaborador por su ID
    @DeleteMapping("/deactivate/{id}")
    fun deleteColaboradorById(@PathVariable id: Long): ResponseEntity<Void> {
        colaboradorService.deactivateColaboradorById(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para activar un colaborador por su ID
    @PutMapping("/activate/{id}")
    fun activateColaboradorById(@PathVariable id: Long): ResponseEntity<Void> {
        colaboradorService.activateColaboradorById(id)
        return ResponseEntity.noContent().build()
    }

    // Metodo para editar un colaborador
    @PutMapping("/{id}")
    fun editColaborador(@PathVariable id: Long, @RequestBody @Valid updatedColaboradorData: UpdateDataColaborador): ResponseEntity<Void> {
        colaboradorService.editColaborador(id, updatedColaboradorData)
        return ResponseEntity.noContent().build()
    }
}