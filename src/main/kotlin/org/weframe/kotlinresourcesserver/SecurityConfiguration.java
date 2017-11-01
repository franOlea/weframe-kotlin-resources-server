package org.weframe.kotlinresourcesserver;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
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
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/login").permitAll()
        .antMatchers(HttpMethod.GET, BACKBOARDS_URL).hasAuthority("read:backboards")
        .antMatchers(HttpMethod.POST, BACKBOARDS_URL).hasAuthority("create:backboards")
        .antMatchers(HttpMethod.DELETE, BACKBOARDS_URL).hasAuthority("delete:backboards")
        .antMatchers(HttpMethod.GET, PICTURES_URL).hasAuthority("read:pictures")
        .antMatchers(HttpMethod.POST, PICTURES_URL).hasAuthority("create:pictures")
        .antMatchers(HttpMethod.DELETE, PICTURES_URL).hasAuthority("delete:pictures")
        .anyRequest().authenticated();
  }
}