package com.ucsm.conecta.ucsmconecta.services.universidad.congresos.asistencia

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.asistencia.Asistencia
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import com.ucsm.conecta.ucsmconecta.domain.users.participante.Participante
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.asistencia.DataRequestAsistencia
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.asistencia.AsistenciaRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.bloques.BloqueService
import com.ucsm.conecta.ucsmconecta.services.users.ParticipanteService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDate
import java.time.LocalTime

@Service
class AsistenciaService @Autowired constructor(
    private val asistenciaRepository: AsistenciaRepository,
    private val participanteService: ParticipanteService,
    private val bloqueService: BloqueService,
    private val congresoService: CongresoService
) {
    // Metodo para crear una nueva asistencia
    @Transactional
    fun createAsistencia(@RequestBody @Valid dataRequestAsistencia: DataRequestAsistencia): Asistencia {
        // Buscar el participante asociado
        val participante: Participante = participanteService.getParticipanteById(dataRequestAsistencia.participanteId)

        // Buscar el bloque asociado
        val bloque: Bloque = bloqueService.getBloqueById(dataRequestAsistencia.bloqueId)

        // Buscar el congreso asociado
        val congreso: Congreso = congresoService.getCongresoById(dataRequestAsistencia.congresoId)

        // Verificar si ya existe una asistencia para el mismo participante en el mismo bloque y congreso
        if (asistenciaRepository.existsByParticipanteIdAndBloqueIdAndCongresoId(participante.id!!, bloque.id!!, congreso.id!!)) {
            throw IllegalArgumentException("La asistencia para este participante en el bloque y congreso ya existe")
        }

        // Obtener hora y fecha actual del sistema
        val fechaActual = LocalDate.now()
        val horaActual = LocalTime.now()

        // ✅ 1. Verificar que la fecha coincida con la del bloque
        if (!fechaActual.equals(bloque.dia.fecha)) {
            throw IllegalArgumentException("No se puede registrar asistencia fuera de la fecha del bloque (${bloque.dia.fecha})")
        }

        // ✅ 2. Verificar que la hora esté dentro del rango del bloque
        if (horaActual.isBefore(bloque.horaInicio) || horaActual.isAfter(bloque.horaFinal)) {
            throw IllegalArgumentException(
                "No se puede registrar asistencia fuera del horario del bloque (${bloque.horaInicio} - ${bloque.horaFinal})"
            )
        }

        return asistenciaRepository.save(Asistencia(
            participante = participante,
            bloque = bloque,
            congreso = congreso
        ))
    }

    // Metodo para obtener la asistencia por id
    fun getAsistenciaById(id: Long) = asistenciaRepository.findById(id).
            orElseThrow { ResourceNotFoundException("Asistencia con id $id no encontrado") }
}