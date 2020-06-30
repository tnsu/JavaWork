package com.lec.sts15_mybatis.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.BWriteDTO;
import com.lec.sts15_mybatis.board.command.BCommand;
import com.lec.sts15_mybatis.board.command.BDeleteCommand;
import com.lec.sts15_mybatis.board.command.BListCommand;
import com.lec.sts15_mybatis.board.command.BSelectCommand;
import com.lec.sts15_mybatis.board.command.BUpdateCommand;
import com.lec.sts15_mybatis.board.command.BViewCommand;
import com.lec.sts15_mybatis.board.command.BWriteCommand;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BCommand command;
	private JdbcTemplate template;
	
	
	
	public BoardController() {
		super();
		System.out.println("BoardController() 생성");
	}



	@Autowired
	public void setTemplate(JdbcTemplate template) { // 외부에서만들어진
		System.out.println("setTemplate() 호출");
		this.template = template;
		C.template = template;
	}
	
	@RequestMapping("/list.do")
	public String list(Model model) { // 빈 model 을 생성하고 
		command = new BListCommand();
		command.execute(model); // 
		return "board/list";
	}
	
	@RequestMapping("/write.do")
	public String write(Model model) {
		return "board/write";
	}
	
	@PostMapping("/writeOk.do")
	public String writeOk(BWriteDTO dto , Model model) {//커맨드 객체(BWriteDTO)로 request parameter 들을 한방에 담아옴
		model.addAttribute("dto",dto);
		new  BWriteCommand().execute(model);
		return "board/writeOk";
		
	}
	
	@RequestMapping("/view.do")
	public String view(int uid, Model model) {
		model.addAttribute("uid",uid);
		new BViewCommand().execute(model);
		return "board/view";
	}
	
	@RequestMapping("/update.do")
	public String update(int uid, Model model) {
		model.addAttribute("uid", uid);
		new BSelectCommand().execute(model);
		return "board/update";
	}
	@PostMapping("/updateOk.do")
	public String updateOk(BWriteDTO dto, Model model) {
		model.addAttribute("dto", dto);
		new BUpdateCommand().execute(model);
		return "board/updateOk";
	}
	@PostMapping("/deleteOk.do")
	public String deleteOk(int uid, Model model) {
		model.addAttribute("uid",uid);
		new BDeleteCommand().execute(model);
		return "board/deleteOk";
	}
	
	
	
	
}
