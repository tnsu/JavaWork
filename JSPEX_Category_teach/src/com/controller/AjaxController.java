package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.CateListCommand;
import com.command.Command;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("*.ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  /cate_list.ajax   : 목록 요청
		System.out.println("ajaxAction()");
		
		request.setCharacterEncoding("UTF-8");
		
		// 컨트롤러는 아래 두개를 갖고 있어야 한다.
		String viewPage = null;   // 어떠한 페이지를 보여줄지
		Command command = null;   // 어떠한 로직을 수행할지
		
		// URL로부터 URI, ContextPath, Command 분리
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		// 테스트 출력
		System.out.println("uri: " + uri);  
		System.out.println("conPath: " + conPath);  
		System.out.println("com: " + com);
		
		// 컨트롤러는 커맨드에 따라, 로직을 수행을 하고
		// 결과를 내보낼 view를 결정한다
		switch(com) {
		case "/cate_list.ajax":  // 목록요청
			new CateListCommand().execute(request, response);
			break;
		} // end switch
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
