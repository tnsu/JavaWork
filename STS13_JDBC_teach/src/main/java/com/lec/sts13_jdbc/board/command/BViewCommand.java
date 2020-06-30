package com.lec.sts13_jdbc.board.command;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;

public class BViewCommand implements BCommand{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap(); 
		BWriteDAO dao = new BWriteDAO();
		BWriteDTO dto = (BWriteDTO)map.get("uid");  
		List<BWriteDTO> list = dao.readByUid(dto);
		model.addAttribute("list",list);
	}

}
