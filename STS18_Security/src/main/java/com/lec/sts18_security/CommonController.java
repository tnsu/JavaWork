package com.lec.sts18_security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		System.out.println("access Denied : " + auth);
		model.addAttribute("msg", "접근 권한 거부");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		System.out.println("error : " + error);
		System.out.println("logout : " + logout);
		
		if(error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		if(logout != null) {
			model.addAttribute("logout", "LOGOUT!");
		}
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		System.out.println("custom logout");
	}
	
	// 로그아웃이 되있는 상태에서 로그아웃을 했기 때문에 찍히지 않고 로그인 페이지로 이동하게 된다. 
	@PostMapping("/customLogout")
	public void logoutPost() {
		System.out.println("post custom logout");
	}

}
