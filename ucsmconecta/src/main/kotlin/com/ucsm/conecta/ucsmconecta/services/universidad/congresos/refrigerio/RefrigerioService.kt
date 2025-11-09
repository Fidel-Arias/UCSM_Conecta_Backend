package com.ucsm.conecta.ucsmconecta.services.universidad.congresos.refrigerio

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.refrigerio.Refrigerio
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.refrigerio.DataRequestRefrigerio
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.refrigerio.RefrigerioRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.services.users.ParticipanteService
import jakarta.transaction.Transactional
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
    @Transactional
    fun createRefrigerio(@RequestBody @Valid dataRequestRefrigerio: DataRequestRefrigerio): Refrigerio {
        // Buscar participante asociado al refrigerio
        val participante = participanteService.searchByNumDocumento(dataRequestRefrigerio.documentoParticipante)

        // Buscar congreso asociado al refrigerio
        val congreso = congresoService.searchByCodigo(dataRequestRefrigerio.congresoCod)

        // Validar la cantidad de refrigerios asignados al participante en el congreso
        val refrigeriosAsignados = refrigerioRepository.countByParticipante_IdAndCongreso_Id(participante.id!!, congreso.id!!)

        if (refrigeriosAsignados >= congreso.numRefrigerios) {
            throw IllegalStateException("Límite alcanzado")
        }

        // Crear refrigerio
        return refrigerioRepository.save(
            Refrigerio(
                participante = participante,
                congreso = congreso
            )
        )
    }

    // Metodo para obtener refrigerio por id
    fun getAllRefrigeriosByNumDocumento(numDocumento: String): List<Refrigerio> {
        return refrigerioRepository.findByParticipante_NumDocumento(numDocumento)
    }

    //Metodo para obtener el refrigerio mas reciente del participante por su numero de documento
    fun getRefrigerioParticipanteByNumDocumento(numDocumento: String): Refrigerio = refrigerioRepository.findTopByParticipante_NumDocumentoOrderByFechaDescHoraDesc(numDocumento)
        .orElseThrow { ResourceNotFoundException("El participante con el numero de documento $numDocumento no tiene refrigerios registrados") }

}