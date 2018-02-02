package org.weframe.kotlinresourcesserver;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.weframe.kotlinresourcesserver.product.picture.file.InMemoryPictureFileService;
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService;

@EnableWebSecurity
@EnableJpaAuditing
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String BACKBOARDS_URL = "/backboards/**";
  private static final String PICTURES_URL = "/pictures/**";

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
        .antMatchers(HttpMethod.GET, "/users/me").authenticated()
        .antMatchers(HttpMethod.GET, BACKBOARDS_URL).hasAuthority("read:backboards")
        .antMatchers(HttpMethod.POST, BACKBOARDS_URL).hasAuthority("create:backboards")
        .antMatchers(HttpMethod.DELETE, BACKBOARDS_URL).hasAuthority("delete:backboards")
        .antMatchers(HttpMethod.GET, PICTURES_URL).hasAuthority("read:pictures")
        .antMatchers(HttpMethod.POST, PICTURES_URL).hasAuthority("create:pictures")
        .antMatchers(HttpMethod.DELETE, PICTURES_URL).hasAuthority("delete:pictures")
        .anyRequest().permitAll();
  }

  @Bean
  CorsConfigurationSource getCorsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  AuditorAware<String> getAuditorAware() {
    return new SpringSecurityAuditorAware();
  }

  PictureFileService pictureFileService() {
    return new InMemoryPictureFileService("127.0.0.1","8080");
  }
}