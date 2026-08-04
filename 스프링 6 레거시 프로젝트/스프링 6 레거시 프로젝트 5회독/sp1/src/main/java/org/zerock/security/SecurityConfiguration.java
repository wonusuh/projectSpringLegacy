package org.zerock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfiguration {
//  private final CustomAuthSuccessHandler customAuthSuccessHandler;

//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//      return web ->
//              web.ignoring().requestMatchers("/favicon.ico", "/resources/**", "/error");
//  }

//  @Bean
//  AuthenticationFailureHandler customAuthFailureHandler() {
//      return new CustomAuthFailureHandler();
//  }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("=== === === securityFilterChain === === ===");

        // 로그인 설정
        httpSecurity.formLogin((config) -> {
            //
        });

        // Cross-Site Request Forgery 설정
        httpSecurity.csrf((config) -> {
            config.disable(); // 사용안함
        });

        // 403 핸들러
        httpSecurity.exceptionHandling((handler) -> {
            handler.accessDeniedHandler(new Custom403Handler());
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
