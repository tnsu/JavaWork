package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/InitS"},
			initParams={
		@WebInitParam(name="id", value="test11"), 
		@WebInitParam(name="pw", value="1000"), 
		@WebInitParam(name="local", value="pusan")
	}
)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	ServletConfig 의 메소드를 사용.
		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		String local = getInitParameter("local");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("id : " + id + "<br>");
		out.println("비밀번호 :" + pw + "<br>");
		out.println("지역 :" + local + "<br>");
		
		/*
		// html 형식으로 출력
		out.println("<!DOCTYPE html>");
		out.println("<html lang='ko'>");
		out.println("<head>");
		out.println("<meta charset='utf-8'");
		out.println("</head>");
		out.println("<body>");
		out.println("id : " + id + "<br>");
		out.println("비밀번호 :" + pw + "<br>");
		out.println("지역 :" + local + "<br>");
		out.println("</body>");
		out.println("</html>");
		*/
		out.close();
		
		// 콘솔화면에 출력
		System.out.println("id : " + id);
		System.out.println("비밀번호 :" + pw);
		System.out.println("지역 :" + local);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
