package com.ucsm.conecta.ucsmconecta.services.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.refrigerio.Refrigerio
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio.DataRequestRefrigerio
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.refrigerio.RefrigerioRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.services.users.ParticipanteService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class RefrigerioService @Autowired constructor(
    private val refrigerioRepository: RefrigerioRepository,
    private val participanteService: ParticipanteService,
    private val congresoService: CongresoService
) {
    // Metodo para crear refrigerio
    fun createRefrigerio(@RequestBody @Valid dataRequestRefrigerio: DataRequestRefrigerio): Refrigerio {
        // Buscar participante asociado al refrigerio
        val participante = participanteService.getParticipanteById(dataRequestRefrigerio.participanteId)

        // Buscar congreso asociado al refrigerio
        val congreso = congresoService.getCongresoById(dataRequestRefrigerio.congresoId)

        // Validar la cantidad de refrigerios asignados al participante en el congreso
        val refrigeriosAsignados = refrigerioRepository.countByParticipanteAndCongreso(participante.id!!, congreso.id!!)

        if (refrigeriosAsignados >= congreso.numRefrigerios) {
            throw IllegalStateException("Limite de refrigerios alcanzados para este congreso")
        }

        // Crear refrigerio
        return refrigerioRepository.save(
            Refrigerio(
                fecha = dataRequestRefrigerio.fecha,
                participante = participante,
                congreso = congreso
            )
        )
    }

    // Metodo para obtener refrigerio por id
    fun getRefrigerioById(refrigerioId: Long): Refrigerio {
        return refrigerioRepository.findById(refrigerioId).orElseThrow {
            ResourceNotFoundException("Refrigerio con id $refrigerioId no encontrado")
        }
    }

    // Metodo para eliminar refrigerio por id
    fun deleteRefrigerioById(id: Long) {
        // Obtener refrigerio
        val refrigerio = getRefrigerioById(id)
        // Eliminar refrigerio
        refrigerioRepository.delete(refrigerio)
    }
}