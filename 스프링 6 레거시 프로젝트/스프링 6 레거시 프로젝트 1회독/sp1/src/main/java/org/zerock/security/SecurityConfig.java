package org.zerock.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private DataSource dataSource;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("---------------------------security config------------------------------");

		http.formLogin((config) -> {
			config.loginPage("/account/login");
			config.successHandler(new CustomLoginSuccessHandler());
		});

		http.rememberMe((config) -> {
			config.key("my key");
			config.tokenRepository(persistentTokenRepository());
			config.tokenValiditySeconds(60 * 60 * 24 * 30);// 30일
		});

		http.logout((config) -> {
			config.deleteCookies("JSESSIONID", "remember-me");
		});

		http.csrf((config) -> {
			config.disable();
		});

		http.exceptionHandling((handler) -> {
			handler.accessDeniedHandler(new Custom403Handler());
		});

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		 tokenRepository.setCreateTableOnStartup(true); // 테이블 자동 생성 - 추천하지 않음
		return tokenRepository;
	}
}
