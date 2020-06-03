package com.command.write;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.FileDAO;
import com.lec.beans.FileDTO;
import com.lec.beans.WriteDAO;
import com.lec.beans.WriteDTO;
public class SelectCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();
		WriteDTO [] arr = null;
		
		//첨부파일 정보 받아오기 (update.jsp 화면에 보여주어야한다
		FileDAO fileDao = new FileDAO();
		FileDTO [] fileArr = null;
		int uid = Integer.parseInt(request.getParameter("uid"));  // 매개변수 검증 필요

		try {
			arr = dao.selectByUid(uid);  // 읽기 only (null은 아니지만 비어있는 배열로 리턴하기 때문에 1)
			request.setAttribute("list", arr);
			
			if(arr != null && arr.length == 1) { 
				fileArr = fileDao.selectFilesByWrUid(uid); // uid 에 저장되어있는 
				request.setAttribute("file", fileArr);
			}
			
		} catch (SQLException e) { // 만약 ConnectionPool 을 사용한다면 여기서 NamingException 도 catch 해야 한다  
			e.printStackTrace();
		}
	}

}
