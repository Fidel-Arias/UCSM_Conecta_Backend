package com.ucsm.conecta.ucsmconecta.infra.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = header.substring(7)

        try {
            if (jwtUtil.validateToken(token)) {
                // Extraer email o numDocumento
                val username = jwtUtil.getUsernameFromToken(token)
                val role = jwtUtil.getRole(token)

                // Crear los authorities (roles)
                val authorities = listOf(SimpleGrantedAuthority(role))

                // Crear objeto de autenticación
                val auth = UsernamePasswordAuthenticationToken(username, null, authorities)

                // Registrar autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (ex: Exception) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("Token inválido o expirado")
            return
        }

        filterChain.doFilter(request, response)
    }
}
