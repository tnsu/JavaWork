package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.CategoryDAO;
import com.lec.beans.CategoryDTO;

public class CateListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CategoryDAO dao = new CategoryDAO();
		CategoryDTO[] arr = null;

		// ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL

		String deString = request.getParameter("depth");
		String pString = request.getParameter("parent");
		int parent = 0;
		int depth = 1;

		if (deString == null || deString.trim().equals("")) {
			depth =1;
		} else {
			depth = Integer.parseInt(deString);
		}
		if (depth == 1) {
			try {
				arr = dao.selectDepth1();
				status = "OK";
				request.setAttribute("list", arr);
			} catch (SQLException e) {
				
			}// end try(deString depth1 
		}else {
			if(pString == null || pString.trim().equals("")) {
				message.append("parent is null");
				status = "OK";	
			}else {
				try {
					parent = Integer.parseInt(pString);
					arr = dao.selectDepthN(depth, parent);	
//					if(arr == null || arr.length == 0) {
//						status = "FAIL";
//						message.append("불러올수 없규");
//					}else {
						status = "OK";	
//					}
					request.setAttribute("list", arr);
				}catch (NumberFormatException e) {
					// parent 가 숫자가 아니면
					message.append("parent 숫자로 안들어옴");
				} catch (SQLException e) {
					// SQL 오류
					message.append("SQL 접근 에러 : " + e.getMessage());
				} // end try-catch
			}
		}
		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());
	}// end exe
}// end Controller
