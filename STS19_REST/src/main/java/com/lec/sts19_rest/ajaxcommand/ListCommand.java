package com.lec.sts19_rest.ajaxcommand;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lec.sts19_rest.board.C;
import com.lec.sts19_rest.board.beans.BWriteDTO;
import com.lec.sts19_rest.board.beans.IAjaxDAO;


public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		IAjaxDAO dao = C.sqlSession.getMapper(IAjaxDAO.class);
		List<BWriteDTO> arr = null;
		
		//ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL
		
		// 페이징 관련 세팅값들 
		int page = 1; // 현재 페이지(디폴트는 1page)
		int pageRows = 8; // 한 페이지 에 몇개의 글을 리스트 ?
		int writePages = 10; // 한[페이징]에 몇개의 '페이지'를 표시 
		int totalCnt =0;// 글은 총 몇개 인가
		int totalPage =0; // 총 몇' 페이지' 분량인지;
		
		// 매개변수 검증 하려고만듬 (String 타입이라서 , 전에는 검증을 안하고 진행함)
		String param;		
		
		// page 값 : 현재 몇 페이지
		param = request.getParameter("page");
		if(param != null && param.trim().length() != 0) { // null 도아니고 비여있는 문자열도 아니면
			try {
				page = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				// 예외 처리 안함
			}
		} // 값이 없어도(if)문 실패시 page 값은 1로 남아있고,성공할 시 해당하는 페이지 번호가 page 에 들어감
		
		// pageRows 값 : 한 페이지 에 몇개의 글?
		param = request.getParameter("pageRows");
		if(param != null && param.trim().length() != 0) { // null 도아니고 비여있는 문자열도 아니면
			try {
				pageRows = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				// 예외 처리 안함
			}
		} 
		
		try {
			/*
			//트랜잭션 수행
			arr =  dao.select();
			
			// "list" 란  name 으로 request 에 arr 값 전달
			// 즉, request 에 담아서 컨트롤러에 전달되는 셈.
			request.setAttribute("list", arr);
			*/
			
			// 글 의 전체 개수 구하기
			totalCnt = dao.countAll();
			
			// 총 페이지 분량은?
			totalPage = (int)Math.ceil(totalCnt/ (double)pageRows);
			
			// 몇번째 row부터 ?
			int fromRow = (page -1) * pageRows +1;  // +1 : 오라클 이 1부터  ROWNUM 시작
			// int fromRow = (page -1) * pageRows;  // MySQL 은 0 부터 시작
			
		
			arr = dao.selectFromRow(fromRow, pageRows);
			
			// 말도 안되는 숫자를 넣으면 arr null 된다
			if(arr == null) {
				message.append("[리스트할 데이터가 없습니다]");
			} else {
				status = "OK";
			}
			
		} catch (Exception e) {
			message.append("[트랜잭션 에러:" + e.getMessage()+ "]");
		}
		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());
		request.setAttribute("list", arr);
		
		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("writePages", writePages);
		request.setAttribute("pageRows", pageRows);
		request.setAttribute("totalCnt", totalCnt);
	}

}
