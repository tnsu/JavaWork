package com.lec.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCycle
 */
@WebServlet("/Cycle")
public class ServletCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	// 서블릿 생성자, 첫 request 발생시 최초 단 한번 생성
    public ServletCycle() {
        super();
     System.out.println("서블릿 생성");
    }
    
    // 서블릿 객체생성 직후(이후) 호출
    @Override
    public void init() throws ServletException {
		System.out.println("init 호출"); // 최초에 단 한번 호출된다.
    }
    
    //서블릿 객체 소멸시 호출
    @Override
    public void destroy() {
    	System.out.println("destoy 호출");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 호출");

	}
	
	@Override // doGET, doPort 없으면,  service() 가 실행.  같이 있어도 service()가 실행.
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 호출");
	}
}
