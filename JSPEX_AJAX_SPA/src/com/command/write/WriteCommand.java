package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;

public class WriteCommand  implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		
		//ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL
		
		
		// 매개변수 받아오기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// 유효성 체크  null 이거나, 빈문자열이면 이전화면으로 돌아가기
		if(name != null && subject != null && 
				name.trim().length() > 0 && subject.trim().length() > 0){			
			try {			
				cnt = dao.insert(subject, content, name); // 성공시 cnt =1
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end if
		request.setAttribute("result", cnt);
		
	} // end execute()
} // end Command

