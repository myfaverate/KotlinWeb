package edu.tyut.spring_boot_security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@EnableMethodSecurity(prePostEnabled = true)
@Configuration
class AppSecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests { it: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
            it.requestMatchers(
                "/"
            ).permitAll().anyRequest().authenticated()
        }.formLogin{ it:  FormLoginConfigurer<HttpSecurity> ->
            it.loginPage("/login").permitAll()
        }.build()
    }
    @Bean
    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val admin: UserDetails = User.builder().passwordEncoder { passwordEncoder.encode(it) }.username("admin")
            .password("111111").roles("USER").authorities("write").build()
        val tom: UserDetails = User.builder().passwordEncoder { passwordEncoder.encode(it) }.username("tom").password("111111").roles("USER").build()
        val jack: UserDetails = User.builder().passwordEncoder { passwordEncoder.encode(it) }.username("jack").password("111111").roles("USER").build()
        return InMemoryUserDetailsManager(admin, tom, jack)
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}