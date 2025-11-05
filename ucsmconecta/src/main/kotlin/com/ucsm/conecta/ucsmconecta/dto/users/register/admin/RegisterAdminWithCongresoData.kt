package com.ucsm.conecta.ucsmconecta.dto.users.register.admin

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataRequestCongreso
import jakarta.validation.Valid

data class RegisterAdminWithCongresoData(
    @field:Valid
    val admin: RegisterAdminData,

    @field:Valid
    val congreso: DataRequestCongreso
)
