package org.zerock.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("authentication : " + authentication);

		// 세션에서 SavedRequest 확인
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
		log.info(savedRequest);

		if (savedRequest != null) {
			response.sendRedirect(savedRequest.getRedirectUrl());
		} else {
			response.sendRedirect("/board/list");
		}
	}
}
