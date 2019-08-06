package org.weframe.kotlinresourcesserver;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String ADMIN_ROLE = "ADMIN";
  private static final String BACKBOARDS_URL = "/backboards/**";
  private static final String PICTURES_URL = "/pictures/**";
  private static final String FRAMES_URL = "/frames/**";
  private static final String MAT_TYPES_URL = "/mat-types/**";
  private static final String FRAME_GLASS = "/frame-glasses/**";
  private static final String PURCHASE = "/purchases/**";

  @Value(value = "${auth0.apiAudience}")
  private String apiAudience;
  @Value(value = "${auth0.issuer}")
  private String issuer;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    JwtWebSecurityConfigurer
        .forRS256(apiAudience, issuer)
        .configure(http)
        .cors().and()
        .authorizeRequests()
        .antMatchers(HttpMethod.DELETE, FRAMES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.DELETE, BACKBOARDS_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.DELETE, MAT_TYPES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.DELETE, FRAME_GLASS).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.DELETE, PICTURES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.DELETE, PURCHASE).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, FRAMES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, BACKBOARDS_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, MAT_TYPES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.POST, FRAME_GLASS).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PUT, FRAMES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PUT, BACKBOARDS_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PUT, MAT_TYPES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PUT, FRAME_GLASS).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PUT, PURCHASE).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, FRAMES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, BACKBOARDS_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, MAT_TYPES_URL).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, FRAME_GLASS).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.PATCH, PURCHASE).hasRole(ADMIN_ROLE)
        .antMatchers(HttpMethod.GET, "/ping").permitAll()
        .anyRequest().authenticated();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}