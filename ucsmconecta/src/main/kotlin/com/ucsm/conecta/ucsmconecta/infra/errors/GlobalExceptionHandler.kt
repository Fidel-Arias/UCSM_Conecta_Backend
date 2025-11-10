package com.ucsm.conecta.ucsmconecta.infra.errors

import com.ucsm.conecta.ucsmconecta.exceptions.EntityFoundException
import com.ucsm.conecta.ucsmconecta.exceptions.ResourceNotFoundException
import jakarta.el.MethodNotFoundException
import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {
    // ⚠️ Manejo de rutas inexistentes (404)
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFound(ex: NoHandlerFoundException): ResponseEntity<Map<String, Any>> {
        val body = mapOf(
            "status" to 404,
            "error" to "Not Found",
            "message" to "La ruta solicitada no existe: ${ex.requestURL}"
        )
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest::class)
    fun handleBadRequest(ex: HttpClientErrorException.BadRequest): ResponseEntity<Map<String, String>> {
        val mensaje = try {
            // Extraer mensaje de error del JSON del backend
            val errorJson = ex.responseBodyAsString
            Regex("\"error\"\\s*:\\s*\"([^\"]+)\"").find(errorJson)?.groupValues?.get(1)
                ?: "Solicitud inválida"
        } catch (_: Exception) {
            "Solicitud inválida"
        }

        return ResponseEntity
            .badRequest()
            .body(mapOf("error" to mensaje))
    }

    // Error de tipo de dato (conversión fallida)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(ex: MethodArgumentTypeMismatchException): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "error" to "Tipo de dato inválido",
            "mensaje" to "El parámetro '${ex.name}' tiene un valor '${ex.value}' que no se puede convertir a ${ex.requiredType?.simpleName}.",
            "status" to HttpStatus.BAD_REQUEST.value()
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException
    ): ResponseEntity<Map<String, Any>> {

        val response: Map<String, Any> = mapOf(
            "error" to "Error de formato en el cuerpo JSON",
            "mensaje" to "Los datos enviados no tienen el formato esperado o contienen tipos incorrectos.",
            "status" to HttpStatus.BAD_REQUEST.value(),
            "timestamp" to LocalDateTime.now().toString()
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
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

    // Maneja los errores de recursos ya existentes
    @ExceptionHandler(EntityFoundException::class)
    fun handleEntityFound(ex: EntityFoundException): ResponseEntity<Map<String, String>> = ResponseEntity(mapOf("error" to ex.message!!), HttpStatus.FOUND)

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