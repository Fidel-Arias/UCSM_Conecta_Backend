package com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.valoracion.votaciones

import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.DataResultCongreso
import com.ucsm.conecta.ucsmconecta.dto.universidad.congresos.ponencias.DataResultPonencia
import com.ucsm.conecta.ucsmconecta.dto.users.profile.participante.DataResultParticipante

data class DataResponseVotacion(
    val id: Long,
    val score: Int,
    val participante: DataResultParticipante,
    val ponencia: DataResultPonencia,
    val congreso: DataResultCongreso,
)
