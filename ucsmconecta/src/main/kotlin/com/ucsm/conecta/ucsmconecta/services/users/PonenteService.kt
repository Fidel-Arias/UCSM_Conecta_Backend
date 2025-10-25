package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.ponente.Ponente
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes.DataRequestPonente
import com.ucsm.conecta.ucsmconecta.repository.users.ponente.PonenteRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.services.universidad.gradoacademico.GradoAcademicoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class PonenteService @Autowired constructor(
    private val ponenteRepository: PonenteRepository,
    private val gradoAcademicoService: GradoAcademicoService,
    private val congresoService: CongresoService
) {
    fun createPonente(@RequestBody @Valid dataRequestPonente: DataRequestPonente): Ponente {
        // Buscar grado academico relacionado
        val gradoAcademico = gradoAcademicoService.getGradoAcademicoById(dataRequestPonente.gradoAcademicoId)

        // Buscar congreso relacionado
        val congreso = congresoService.getCongresoById(dataRequestPonente.congresoId)

        return ponenteRepository.save(Ponente(
            nombres = dataRequestPonente.nombres,
            apellidos = dataRequestPonente.apellidos,
            gradoAcademico = gradoAcademico,
            congreso = congreso
        ))
    }

    fun getPonenteById(id: Long): Ponente = ponenteRepository.findById(id)
        .orElseThrow { Exception("Ponente no encontrado por su id") }

    fun getAllPonentes(): List<Ponente> = ponenteRepository.findAll()

    fun getPonenteByNombres(nombres: String): Ponente = ponenteRepository.findByNombres(nombres)
        .orElseThrow { Exception("Ponente no encontrado por sus nombres") }

    fun deletePonente(id: Long) = ponenteRepository.deleteById(id)

    fun updatePonente(id: Long, @RequestBody @Valid dataRequestPonente: DataRequestPonente): Ponente {
        // Obtener el ponente existente
        val ponente = getPonenteById(id)

        // Actualizar grado academico relacionado
        val gradoAcademico = gradoAcademicoService.getGradoAcademicoById(dataRequestPonente.gradoAcademicoId)

        // Actualizar campos
        ponente.apply {
            nombres = dataRequestPonente.nombres
            apellidos = dataRequestPonente.apellidos
            this.gradoAcademico = gradoAcademico
        }

        return ponenteRepository.save(ponente)
    }
}