package com.bpm.security.config;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    log.info("securityFilterChain in Web Security implementation");

    httpSecurity
        .cors(
            // org.springframework.security.config.Customizer.withDefaults()
            // Lets try another method
            cors ->
                cors.configurationSource(
                    request -> {
                      CorsConfiguration configuration = new CorsConfiguration();
                      configuration.setAllowedOrigins(Arrays.asList(ResFilter.clientOriginURL));
                      configuration.setAllowedMethods(
                          Arrays.asList(
                              "HEAD", "POST", "GET", "OPTIONS", "DELETE",
                              "PUT")); // cors to allow it
                      configuration.setAllowedHeaders(Arrays.asList("*"));
                      return configuration;
                    }))
        .csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests(
            r -> {
              r.requestMatchers(HttpMethod.OPTIONS)
                  .permitAll()
                  .requestMatchers("/engine-rest/** ")
                  .permitAll()
                  .anyRequest()
                  .anonymous(); // end of pattern config
            })
        .addFilterBefore(
            new ResFilter(), UsernamePasswordAuthenticationFilter.class); // end of line

    return httpSecurity.build();
  }
}
