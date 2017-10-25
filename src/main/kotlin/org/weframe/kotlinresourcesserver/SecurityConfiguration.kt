package org.weframe.kotlinresourcesserver

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.savedrequest.NullRequestCache
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/api/session").permitAll()
                .antMatchers("/signin/facebook").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/browser/**").permitAll()
                .antMatchers("/**").authenticated()
                .and().headers().frameOptions().disable() // for h2
                .and().requestCache().requestCache(NullRequestCache())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().cors()
                .and().csrf().disable()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("localhost", "*", "**")
        configuration.allowedMethods = listOf("GET", "POST", "*", "**")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}