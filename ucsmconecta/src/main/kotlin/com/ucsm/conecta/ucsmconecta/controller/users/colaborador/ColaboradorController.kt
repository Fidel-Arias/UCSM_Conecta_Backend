package com.ucsm.conecta.ucsmconecta.controller.users.colaborador

import com.ucsm.conecta.ucsmconecta.domain.universidad.carrera.EscuelaProfesional
import com.ucsm.conecta.ucsmconecta.domain.users.colaborador.Colaborador
import com.ucsm.conecta.ucsmconecta.dto.users.auth.colaborador.RegisterColaboradorData
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.ColaboradorBusquedaDTO
import com.ucsm.conecta.ucsmconecta.dto.users.profile.colaborador.DataResponseColaborador
import com.ucsm.conecta.ucsmconecta.services.universidad.carrera.EscuelaProfesionalService
import com.ucsm.conecta.ucsmconecta.services.users.ColaboradorService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/colaboradores")
class ColaboradorController @Autowired constructor(
    private val colaboradorService: ColaboradorService,
    private val escuelaProfesionalService: EscuelaProfesionalService
){
    @PostMapping
    @Transactional
    fun createColaborador(@RequestBody @Valid registerColaboradorData: RegisterColaboradorData, uriComponentsBuilder: ServletUriComponentsBuilder): ResponseEntity<DataResponseColaborador> {
        // Crear el colaborador
        val colaborador = colaboradorService.createColaborador(registerColaboradorData)

        // Buscar la escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.getEscuelaProfesionalById(registerColaboradorData.escuelaProfesionalId)

        // Se pasan los datos creados a DataResponseColaborador para visualizarlos
        val dataResponseColaborador = DataResponseColaborador(
            id = colaborador.id!!,
            nombres = colaborador.nombres,
            aPaterno = colaborador.aPaterno,
            aMaterno = colaborador.aMaterno,
            email = colaborador.email,
            estado = colaborador.estado,
            escuelaProfesional = escuelaProfesional?.id
        )
        // Construir la URI del nuevo recurso creado
        val uri = uriComponentsBuilder.path("/api/colaboradores/{id}")
            .buildAndExpand(colaborador.id).toUri()

        return ResponseEntity.created(uri).body(dataResponseColaborador)
    }

    @GetMapping("/{id}")
    fun getColaboradorById(@PathVariable id: Long): ResponseEntity<DataResponseColaborador> {
        val colaborador: Colaborador? = colaboradorService.getColaboradorById(id)

        // Buscar la escuela profesional asociada
        val escuelaProfesional: EscuelaProfesional? = escuelaProfesionalService.getEscuelaProfesionalById(colaborador?.escuelaProfesional?.id!!)

        val dataResponseColaborador: DataResponseColaborador = DataResponseColaborador(
            id = colaborador.id!!,
            nombres = colaborador.nombres,
            aPaterno = colaborador.aPaterno,
            aMaterno = colaborador.aMaterno,
            email = colaborador.email,
            estado = colaborador.estado,
            escuelaProfesional = escuelaProfesional?.id
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
                escuelaProfesional = colaborador.escuelaProfesional?.id
            )
        }

        return ResponseEntity.ok(dataResponseColaborador)
    }

    @GetMapping("/search/nombres/{nombres}")
    fun searchColaboradoresByNombres(@PathVariable nombres: String): ResponseEntity<List<ColaboradorBusquedaDTO>> {
        // Buscar colaboradores por nombres
        val colaboradores: List<ColaboradorBusquedaDTO> = colaboradorService.searchByNombres(nombres)
            .map { colaborador ->
                ColaboradorBusquedaDTO(
                    nombres = colaborador.nombres,
                    apPaterno = colaborador.apPaterno,
                    apMaterno = colaborador.apMaterno,
                    email = colaborador.email,
                    estado = colaborador.estado,
                )
            }

        return ResponseEntity.ok(colaboradores)
    }
}