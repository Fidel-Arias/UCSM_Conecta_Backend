package com.ucsm.conecta.ucsmconecta.services.users

import com.ucsm.conecta.ucsmconecta.domain.users.ponente.Ponente
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponentes.DataRequestPonente
import com.ucsm.conecta.ucsmconecta.repository.users.ponente.PonenteRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.services.universidad.gradoacademico.GradoAcademicoService
import jakarta.transaction.Transactional
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
    // Metodo para crear un nuevo ponente
    @Transactional
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

    // Método para obtener un ponente por su ID
    fun getPonenteById(id: Long): Ponente = ponenteRepository.findById(id)
        .orElseThrow { Exception("Ponente no encontrado por su id") }

    // Método para obtener todos los ponentes
    fun getAllPonentes(): List<Ponente> = ponenteRepository.findAll()

    // Método para obtener un ponente por sus nombres
    fun getPonenteByNombres(nombres: String): Ponente = ponenteRepository.findByNombres(nombres)
        .orElseThrow { Exception("Ponente no encontrado por sus nombres") }

    // Método para eliminar un ponente por su ID
    @Transactional
    fun deletePonente(id: Long) = ponenteRepository.deleteById(id)

    // Método para actualizar un ponente
    @Transactional
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