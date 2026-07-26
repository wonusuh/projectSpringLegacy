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
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Log4j2
public class SecurityConfiguration {
	private final DataSource dataSource;

	@Autowired
	public SecurityConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		log.info("========== security config ==========");

		// 로그인 폼 설정
		httpSecurity.formLogin((config) -> {
			config.loginPage("/account/login");
			config.successHandler(new CustomLoginSuccessHandler());
		});

		// 자동 로그인 설정
		httpSecurity.rememberMe((config) -> {
			config.key("my key");
			config.tokenRepository(persistentTokenRepository());
			config.tokenValiditySeconds(60 * 60 * 24 * 30);
		});

		// Cross-Site Request Forgery 설정
		httpSecurity.csrf((config) -> {
			config.disable(); // 사용 안함
		});

		// 403 핸들러 설정
		httpSecurity.exceptionHandling((handler) -> {
			handler.accessDeniedHandler(new Custom403Handler());
		});

		// 로그아웃 설정
		httpSecurity.logout((config) -> {
			config.deleteCookies("JSESSIONID", "remember-me");
		});

		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// tokenRepository.setCreateTableOnStartup(true); // 테이블 자동생성 비추천
		return tokenRepository;
	}
}
