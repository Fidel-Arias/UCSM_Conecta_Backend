package com.ucsm.conecta.ucsmconecta.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
open class SecurityConfig(
    private val jwtFilter: JwtAuthenticationFilter
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    //Permitir archivos estáticos y recursos públicos
                .requestMatchers(
                "/", "/index.html", "/app.html", "/main.css", "/app.js",
                "/ws/**", "/topic/**", "/sockjs/**", "/index.html/info", "/qrcodes/**").permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/grados-academicos/**").permitAll()
                    .requestMatchers("/api/escuelas-profesionales/**").permitAll()
                    .requestMatchers("/api/tipos-participantes/**").permitAll()
                    .requestMatchers("/api/register/administrador").permitAll()
                    .requestMatchers("/api/administrador/**").hasAuthority("ADMIN")
                    .requestMatchers("/api/colaborador/**").hasAnyAuthority("COLABORADOR")
                    .requestMatchers("/api/participante/**").hasAnyAuthority("PARTICIPANTE")
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
        return authenticationConfiguration.authenticationManager
    }

    /*
    * Spring Security espera que las contraseñas estén codificadas utilizando un PasswordEncoder
    */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder() // Usamos BCrypt para codificar contraseñas
    }
}