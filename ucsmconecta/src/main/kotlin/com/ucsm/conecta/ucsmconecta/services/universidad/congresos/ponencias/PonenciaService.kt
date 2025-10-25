package com.ucsm.conecta.ucsmconecta.services.universidad.congresos.ponencias

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.Congreso
import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.ponencias.Ponencia
import com.ucsm.conecta.ucsmconecta.domain.users.ponente.Ponente
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataRequestPonencia
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.ponencias.PonenciaRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.CongresoService
import com.ucsm.conecta.ucsmconecta.services.users.PonenteService
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class PonenciaService @Autowired constructor(
    private val ponenciaRepository: PonenciaRepository,
    private val ponenteService: PonenteService,
    private val congresoService: CongresoService,
) {
    // Metodo para crear una nueva ponencia
    @Transactional
    fun createPonencia(@RequestBody @Valid dataRequestPonencia: DataRequestPonencia): Ponencia {
        // Buscar ponente relacionado
        val ponente: Ponente = ponenteService.getPonenteById(dataRequestPonencia.ponenteID)

        // Buscar congreso relacionado
        val congreso: Congreso = congresoService.getCongresoById(dataRequestPonencia.congresoId)

        return ponenciaRepository.save(Ponencia(
            nombre = dataRequestPonencia.nombre,
            ponente = ponente,
            congreso = congreso
        ))
    }

    // Metodo para buscar una ponencia por su id
    fun getPonenciaById(id: Long): Ponencia = ponenciaRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Ponencia no encontrada por su id") }

    // Metodo para obtener todas las ponencias activas
    fun getAllPonencias(): List<Ponencia> = ponenciaRepository.findAll()
        .filter { it.estado }

    fun getPonenciaByNombre(nombre: String): Ponencia = ponenciaRepository.findByNombre(nombre)
        .orElseThrow { EntityNotFoundException("Ponencia no encontrada por su nombre") }

    // Metodo para desactivar una ponencia
    fun deactivatePonencia(id: Long): Ponencia {
        val ponencia: Ponencia = getPonenciaById(id)
        ponencia.estado = false
        return ponenciaRepository.save(ponencia)
    }

    // Metodo para activar una ponencia
    fun activatePonencia(id: Long): Ponencia {
        val ponencia: Ponencia = getPonenciaById(id)
        ponencia.estado = true
        return ponenciaRepository.save(ponencia)
    }

    // Metodo para actualizar una ponencia
    fun updatePonencia(id: Long, @RequestBody @Valid dataRequestPonencia: DataRequestPonencia): Ponencia {
        val ponencia: Ponencia = getPonenciaById(id)

        // Buscar ponente relacionado
        val ponente: Ponente = ponenteService.getPonenteById(dataRequestPonencia.ponenteID)

        ponencia.apply {
            nombre = dataRequestPonencia.nombre
            this.ponente = ponente
        }

        return ponenciaRepository.save(ponencia)
    }
}