package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;

public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		
		//ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL
		

		int uid = Integer.parseInt(request.getParameter("uid"));
		if(uid != 0) {
			
		try {
			cnt = dao.deleteByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		request.setAttribute("delete", cnt);
		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());

	}

}
