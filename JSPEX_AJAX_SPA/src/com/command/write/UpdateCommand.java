package com.command.write;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.WriteDAO;

public class UpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		
		//ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL
		

		int uid = Integer.parseInt(request.getParameter("uid"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		if (subject != null && subject.trim().length() > 0) {
			try {
				cnt = dao.update(uid, subject, content);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end if
		request.setAttribute("updateOk", cnt);
		request.setAttribute("upUid", uid);
		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());

	}

}
