package com.ucsm.conecta.ucsmconecta.dto.users.auth

data class LoginResponse(
    val token: String,
    val role: String,
)
