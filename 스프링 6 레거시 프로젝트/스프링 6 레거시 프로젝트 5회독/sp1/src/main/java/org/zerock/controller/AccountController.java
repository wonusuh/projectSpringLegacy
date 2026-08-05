package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/account")
@Log4j2
public class AccountController {
	// 로그인 폼 호출
	@GetMapping("/login")
	public String loginGET() {
		log.info("=== === === loginGET === === ===");
		return "/account/login.jsp";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logoutGET() {
		log.info("=== === === logoutGET === === ===");
		return "/account/logout.jsp";
	}
}
