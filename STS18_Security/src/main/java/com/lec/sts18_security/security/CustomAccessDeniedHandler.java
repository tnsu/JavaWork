package com.lec.sts18_security.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	// request, response 를 매개변수로 사용하기 때문에, 서블릿 API 직접 사용 가능
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		System.out.println("Access Denied Handler");
		System.out.println("redirect 합니다.");
		response.sendRedirect(request.getContextPath() + "/accessError"); // 리다이렉트
	}

}
