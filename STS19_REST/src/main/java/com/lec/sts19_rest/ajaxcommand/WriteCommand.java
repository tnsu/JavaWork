package com.lec.sts19_rest.ajaxcommand;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sts19_rest.board.C;
import com.lec.sts19_rest.board.beans.IAjaxDAO;


public class WriteCommand  implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		IAjaxDAO dao = C.sqlSession.getMapper(IAjaxDAO.class);
		
		//ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL
		
		
		// 매개변수 받아오기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		if(name == null || name.trim().length() == 0) {
			message.append("[유효하지 않는 parmeter : 작성자 필수");
		}else if(subject == null || subject.trim().length() ==0){
			message.append("[유효하지 않는 parmeter : 글제목 필수");
			
		}else {
			try {			
				cnt = dao.insert(subject, content, name); // 성공시 cnt =1
				if(cnt == 0) {
					message.append("[트랜젝션 실패 : 0 insert");
				}else {
					status = "OK"; // 정상적으로 드랜잭션 수행하고 cnt 가 1 일떄 OK 되어야함
				}
			} catch (Exception e) {
//				e.printStackTrace();
				message.append("[트랜잭션 에러 : " + e.getMessage() + "]");
			}
		} // end if
		request.setAttribute("result", cnt);
		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());
		
		
	} // end execute()
} // end Command

