package com.lec.sts16_interceptor.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping	("/user")
public class UserController {
	
	// 예제 : 테스트용 계정
	public static final String ADMIN_ID = "tnti";
	public static final String ADMIN_PW = "1122";
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/loginOk")
	public String loginOk(String id, String pw, HttpSession session) {
		String returnURL = "";
		
		if(session.getAttribute("id") != null) {
			// 기존 id 세션값이 존재한다면 (즉 , 로그인 세션정보가 있는 상태라면)
			session.removeAttribute("id"); // 일단 이전 로그인 세션 정보 삭제
		}
		
		// 실제 회원 DB 테이블을 쿼리해야하는 부분
		if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)) {
			// 로그인 성공
			session.setAttribute("id", id);
			
			// 원래 가고자 했던 url 이 잇었다면
			String priorUrl= (String)session.getAttribute("url_prior_login");
			if(priorUrl != null) {
				returnURL = "redirect:" + priorUrl;
				session.removeAttribute("url_prior_login");
			} else {
				// 없었다면 디폴트로 list.do 로 이동
				returnURL = "redirect:/board/list.do";
			}
		    

			
			returnURL = "redirect:/board/list.do";
		}else {
			// 로그인 실패
			returnURL = "user/logfail";
		}
		
		return returnURL;
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "user/logout";
	}
	
}
