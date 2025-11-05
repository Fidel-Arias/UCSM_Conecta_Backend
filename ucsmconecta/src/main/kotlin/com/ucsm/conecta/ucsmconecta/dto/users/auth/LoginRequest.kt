package com.ucsm.conecta.ucsmconecta.dto.users.auth

data class LoginRequest(
    val email: String? = null,
    val password: String? = null,
    val numDocumento: String? = null
)
