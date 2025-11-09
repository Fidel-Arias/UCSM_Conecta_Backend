package com.ucsm.conecta.ucsmconecta.util

import java.util.UUID

object UUIDGenerator {
    fun generarCodigoUnico(): String {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).uppercase()
    }
}