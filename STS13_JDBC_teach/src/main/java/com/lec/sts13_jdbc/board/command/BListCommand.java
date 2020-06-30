package com.lec.sts13_jdbc.board.command;

import java.util.List;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.board.beans.BWriteDAO;
import com.lec.sts13_jdbc.board.beans.BWriteDTO;

public class BListCommand implements BCommand{

	@Override
	public void execute(Model model) { // controller 에서 넘긴 model 바구니를 받은?
		BWriteDAO dao = new BWriteDAO(); // DAO 생성될때마다 Conneciton 생성하는 행위는 하지 않는다
		List<BWriteDTO> list = dao.select();
		model.addAttribute("list",list);
	}

}
