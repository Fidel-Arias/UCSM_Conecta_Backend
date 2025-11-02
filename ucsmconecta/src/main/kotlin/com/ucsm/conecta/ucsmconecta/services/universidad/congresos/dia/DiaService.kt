package com.ucsm.conecta.ucsmconecta.services.universidad.congresos.dia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.dia.Dia
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.dia.DataRequestDia
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.dia.DiaRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class DiaService @Autowired constructor(
    private val diaRepository: DiaRepository,
    private val congresoService: CongresoService
) {
    // Metodo para crear un nuevo día de congreso
    @Transactional
    fun createDia(@RequestBody @Valid dataRequestDia: DataRequestDia): Dia {
        // Obtener el congreso asociado al día
        val congreso = congresoService.getCongresoById(dataRequestDia.congresoId)

        // Crear un nuevo día a partir de los datos recibidos
        return diaRepository.save(Dia(
            fecha = dataRequestDia.fecha,
            congreso = congreso
        ))
    }

    // Método para obtener un día por su ID
    fun getDiaById(id: Long, includeInactive: Boolean = false): Dia {
        val dia = diaRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Día con id $id no encontrado")
        }

        if (!dia.estado && !includeInactive)
            throw ResourceNotFoundException("Dia con id $id está desactivado o no disponible")

        return dia
    }

    // Método para obtener todos los días
    fun getAllDias(): List<Dia> = diaRepository.findAllByEstadoTrueOrderByIdAsc()

    // Método para desactivar un día
    @Transactional
    fun deactivateDia(id: Long) {
        val dia = getDiaById(id)
        dia.estado = false
        diaRepository.save(dia)
    }

    // Método para activar un día
    @Transactional
    fun activateDia(id: Long) {
        val dia = getDiaById(id, includeInactive = true)
        if (dia.estado)
            throw IllegalStateException("El día ya está activo")
        dia.estado = true
        diaRepository.save(dia)
    }

    // Método para eliminar un día por su ID
    @Transactional
    fun deleteDiaById(id: Long) {
        val dia = getDiaById(id)

        if (!dia.estado)
            throw IllegalStateException("El día ya está activo")
        diaRepository.delete(dia)
    }
}