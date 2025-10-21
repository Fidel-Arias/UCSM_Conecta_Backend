package com.ucsm.conecta.ucsmconecta.services.universidad.gradoacademico

import com.ucsm.conecta.ucsmconecta.domain.universidad.gradoacademico.GradoAcademico
import com.ucsm.conecta.ucsmconecta.dto.universidad.gradoacademico.DataRequestGradoAcademico
import com.ucsm.conecta.ucsmconecta.repository.universidad.gradoacademico.GradoAcademicoRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.Optional

@Service
class GradoAcademicoService @Autowired constructor(
    private val gradoAcademicoRepository: GradoAcademicoRepository
){
    fun createGradoAcademico(@RequestBody @Valid dataRequestGradoAcademico: DataRequestGradoAcademico): GradoAcademico {
        // Lógica para crear un grado académico
        return gradoAcademicoRepository.save(GradoAcademico(
            descripcion = dataRequestGradoAcademico.descripcion,
        ))
    }

    fun getAllGradosAcademicos(): List<GradoAcademico> = gradoAcademicoRepository.findAll()

    fun searchById(id: Long): GradoAcademico = gradoAcademicoRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Grado Academico no encontrado") }

    fun searchByDescripcion(descripcion: String): GradoAcademico = gradoAcademicoRepository.findByDescripcion(descripcion)
        .orElseThrow { EntityNotFoundException("Grado Academico no encontrado") }
}