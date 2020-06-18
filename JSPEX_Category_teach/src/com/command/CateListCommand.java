package com.command;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.beans.AjaxWriteList;
import com.lec.beans.CategoryDAO;
import com.lec.beans.CategoryDTO;

public class CateListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int depth = 1;  // 디폴트 depth1
		int parent = 0;  // 디폴트 parent 0
		
		String depthParam = request.getParameter("depth");
		String parentParam = request.getParameter("parent");
		
		try {			
			if(depthParam != null) depth = Integer.parseInt(depthParam);
			if(parentParam != null) parent = Integer.parseInt(parentParam);
		} catch(Exception e) {
			
		}
		
		CategoryDAO dao = new CategoryDAO();
		CategoryDTO [] dtoArr = null;
		
		try {			
			if(depth == 1) {
				// depth 1 만 읽어 들이면 된다.
				dtoArr = dao.selectByDepth(depth);
			} else {
				// depth >= 2 이면 parent 값에 따라 읽어 들이기
				dtoArr = dao.selectByDepthParent(depth, parent);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			String jsonString = "";
			AjaxWriteList result = new AjaxWriteList();
			
			if(dtoArr == null || dtoArr.length == 0) {
				result.setStatus("FAIL");
				result.setCount(0);
				result.setMessage("0개의 데이터");
			} else {
				result.setStatus("OK");
				result.setCount(dtoArr.length);
				result.setList(Arrays.asList(dtoArr));
				result.setMessage("");
			}
			
			ObjectMapper mapper = new ObjectMapper();
			
			jsonString = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(result);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(jsonString);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	} // end execute()

} 



















