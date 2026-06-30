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
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	log.info("--------------------  security config --------------------");

	httpSecurity.formLogin((config) -> {
	    config.loginPage("/account/login");
	});

	// Cross-Site Request Forgery 사용 안함
	httpSecurity.csrf((config) -> {
	    config.disable();
	});

	// 403 핸들러
	httpSecurity.exceptionHandling((handler) -> {
	    handler.accessDeniedHandler(new Custom403Handler());
	});

	return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(); // 패스워드 암호화
    }
}
