package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SCEx")
public class ServletContextEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletContextEx() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	ServletContext 상속을 받은것이 아님으로 get을 통해 가져와야한다
		// getServletContext() 를 사용하면 ServletContext 객체를 얻어올수 있습니다
		// ServletContext 객체를 통해 초기화 값 얻어오기
		String id = getServletContext().getInitParameter("id");
		String pw = getServletContext().getInitParameter("password");
		String local = getServletContext().getInitParameter("local");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		out.println("id : "+id+"<br>");
		out.println("비밀번호 :" + pw+"<br>");
		out.println("지역 :" +local+"<br>");

		out.close();
		// 콘솔화면에 출력		
		System.out.println("id : "+id);
		System.out.println("pw :"+pw);
		System.out.println("local : "+local);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
