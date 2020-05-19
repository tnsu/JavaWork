package com.lec.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormOk")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletForm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// request.getParameterNames() 
		// Enumeration<String> 값 리턴
		System.out.println("getParameterNames() 사용");
		Enumeration<String> names  = request.getParameterNames();
		while(names.hasMoreElements()){ //가지고있는 것 만큼
			String paramName = names.nextElement();
			String paramValue = request.getParameter(paramName);
			System.out.println(paramName + " : " + paramValue);
		} // end while

		// request.getParameterMap() : 사용
		// Map<String, String[]> 리턴  
		System.out.println("getParameterMap() 사용");
		Map<String, String[]> paramMap = request.getParameterMap();
		for(String key : paramMap.keySet()) {
			System.out.println(key + " : " + Arrays.toString(paramMap.get(key)));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식 ,한글 인코딩 처리 우선!
		request.setCharacterEncoding("utf-8");

		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String[] hobbys = request.getParameterValues("hobby"); // 선택된 값들의 value 들이 담긴다.
		String gender = request.getParameter("gender");
		String local = request.getParameter("local");
		String memo = request.getParameter("memo");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("hidden : " + data1 + ", " + data2 + "<br/>");
		out.println("이름 : " + name + "<br/>");
		out.println("아이디 :" + id + "<br/>");
		out.println("비밀번호 :" + pw + "<br />");
		out.println("취미 :" + Arrays.toString(hobbys) + "<br/>");
		out.println("성별 :" + gender + "<br/>");
		out.println("지역 :" + local + "<br/>");
		out.println("메모 :" + memo + "<br/>");
		out.println("</body></html>");


		out.close(); // out 출력스트림을 닫아줍니다
		
		
		
		// request.getParameterNames() : 안에있는 모든 파라메터의 정보들을 담아온다.
		// Enumeration<String> 값 리턴
		System.out.println("getParameterNames() 사용");
		Enumeration<String> names  = request.getParameterNames();
		while(names.hasMoreElements()){ //가지고있는 것 만큼
			String paramName = names.nextElement();
			String paramValue = request.getParameter(paramName);  // 이렇게 하면. String[] 의 경우 첫번째 값만 나옴, 배열일 때 하나만 나오게 된다.
			System.out.println(paramName + " : " + paramValue);
		} // end while

		// request.getParameterMap() : 사용
		// Map<String, String[]> 리턴   value가 배열이기 때문에 배열 형태롤 뽑힌다. (중복되는 name들의 value의 값들도 다 나올수 있다.)
		System.out.println("getParameterMap() 사용");
		Map<String, String[]> paramMap = request.getParameterMap();
		for(String key : paramMap.keySet()) {
			System.out.println(key + " : " + Arrays.toString(paramMap.get(key)));
		}

	}

}
