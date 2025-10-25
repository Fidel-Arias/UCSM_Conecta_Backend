package com.ucsm.conecta.ucsmconecta.services.universidad.congresos.bloques

import com.ucsm.conecta.ucsmconecta.domain.universidad.congresos.bloques.Bloque
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.bloques.DataRequestBloque
import com.ucsm.conecta.ucsmconecta.repository.universidad.congresos.bloques.BloqueRepository
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.dia.DiaService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.ponencias.PonenciaService
import com.ucsm.conecta.ucsmconecta.services.universidad.congresos.ubicacion.UbicacionService
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class BloqueService @Autowired constructor(
    private val bloqueRepository: BloqueRepository,
    private val ubicacionService: UbicacionService,
    private val ponenciaService: PonenciaService,
    private val diaService: DiaService
) {
    // Metodo para crear un nuevo bloque de congreso
    @Transactional
    fun createBloque(@RequestBody @Valid dataRequestBloque: DataRequestBloque): Bloque {
        // Obtener la ubicación asociada al bloque
        val ubicacion = ubicacionService.getUbicacionById(dataRequestBloque.ubicacionId)

        // Obtener la ponencia asociada al bloque
        val ponencia = ponenciaService.getPonenciaById(dataRequestBloque.ponenciaId)

        // Obtener el día asociado al bloque
        val dia = diaService.getDiaById(dataRequestBloque.diaId)

        // Crear un nuevo bloque a partir de los datos recibidos
        return bloqueRepository.save(Bloque(
            horaInicio = dataRequestBloque.horaInicial,
            horaFinal = dataRequestBloque.horaFinal,
            dia = dia,
            ubicacion = ubicacion,
            ponencia = ponencia
        ))
    }

    // Método para obtener un bloque por su ID
    fun getBloqueById(id: Long): Bloque = bloqueRepository.findById(id).orElseThrow {
        EntityNotFoundException("Bloque con id $id no encontrado")
    }

    // Método para obtener todos los bloques
    fun getAllBloques(): List<Bloque> = bloqueRepository.findAll()

    // Metodo para eliminar un bloque por su ID
    @Transactional
    fun deleteBloqueById(id: Long) = bloqueRepository.deleteById(id)

    // Metodo para actualizar un bloque
    @Transactional
    fun updateBloque(id: Long, @RequestBody @Valid dataRequestBloque: DataRequestBloque): Bloque {
        // Obtener el bloque a actualizar
        val bloque = getBloqueById(id)
        // Obtener la ubicación asociada al bloque
        val ubicacion = ubicacionService.getUbicacionById(dataRequestBloque.ubicacionId)
        // Obtener la ponencia asociada al bloque
        val ponencia = ponenciaService.getPonenciaById(dataRequestBloque.ponenciaId)
        // Obtener el día asociado al bloque
        val dia = diaService.getDiaById(dataRequestBloque.diaId)

        // Actualizar los campos del bloque
        bloque.apply {
            this.horaInicio = dataRequestBloque.horaInicial
            this.horaFinal = dataRequestBloque.horaFinal
            this.ubicacion = ubicacion
            this.ponencia = ponencia
        }
        return bloqueRepository.save(bloque)
    }
}