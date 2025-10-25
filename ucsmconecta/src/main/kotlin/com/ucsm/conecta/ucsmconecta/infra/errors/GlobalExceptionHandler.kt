package com.ucsm.conecta.ucsmconecta.infra.errors

import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import jakarta.el.MethodNotFoundException
import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<Map<String, String>> {
        val body = mapOf("error" to ex.message!!)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errors = ex.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleHttpMessageConversionException(ex: HttpMessageConversionException): ResponseEntity<String> {
        return ResponseEntity("Error de conversión de mensaje HTTP: ${ex.message}", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<String> {
        return ResponseEntity("Credenciales inválidas: ${ex.message}", HttpStatus.UNAUTHORIZED)
    }

    // ⚠️ Captura violaciones de integridad (campos únicos, FK, etc.)
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(ex: DataIntegrityViolationException): ResponseEntity<Map<String, String>> {
        val message = if (ex.message?.contains("numDocumento") == true) {
            "Ya existe un participante con ese número de documento."
        } else {
            "Error de integridad de datos. Verifique los campos únicos o relaciones."
        }

        val body = mapOf("error" to message)
        return ResponseEntity(body, HttpStatus.CONFLICT) // 409 Conflict
    }

    // Maneja el error IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<Map<String, String>> {
        val body = mapOf("error" to ex.message!!)
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    // Maneja los errores de recurso no encontrado en métodos
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to ex.message!!), HttpStatus.NOT_FOUND)

    // Maneja el error IllegalStateException
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<Map<String, String>> {
        val body = mapOf("error" to ex.message!!)
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    // Maneja cualquier otro error no previsto
    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<Map<String, String>> {
        val body = mapOf("error" to (ex.message ?: "Error interno del servidor"))
        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}