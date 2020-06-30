package com.lec.sts13_jdbc.board.command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;

public class BViewCommand implements BCommand{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap(); 
		int uid = (Integer)map.get("uid");   // uid 로 담겨있는 값을 꺼낸 int 에 감는다.
		BWriteDAO dao = new BWriteDAO();
		BWriteDTO dto = dao.readByUid(uid);
		model.addAttribute("list",Arrays.asList(dto));
		
		// Arrays.asList(new String[]{"aaa", "bbb"})
		// Arrays.asList("aaa", "bbb")  // 배열을 만들어서 리스트로 리턴 시키는
		

	}

}
