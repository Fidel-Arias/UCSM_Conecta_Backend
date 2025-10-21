package com.ucsm.conecta.ucsmconecta.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class SecurityConfig {
    // Aquí puedes agregar configuraciones de seguridad adicionales si es necesario
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // Configuraciones de seguridad
        http
            .csrf { csrf -> csrf.disable() } // Deshabilitar CSRF para APIs REST
            .authorizeHttpRequests { auth ->
                auth.anyRequest().permitAll() // Permitir todas las solicitudes
            }
        return http.build()
    }

    /*
    * Spring Security espera que las contraseñas estén codificadas utilizando un PasswordEncoder
    */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder() // Usamos BCrypt para codificar contraseñas
    }
}