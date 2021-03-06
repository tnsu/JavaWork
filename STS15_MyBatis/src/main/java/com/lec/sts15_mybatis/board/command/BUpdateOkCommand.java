package com.lec.sts15_mybatis.board.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.BWriteDTO;
import com.lec.sts15_mybatis.board.beans.IWriteDAO;

public class BUpdateOkCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// Model 안에 있는 값(attribute) 꺼내기
		Map<String, Object> map = model.asMap();  // model 안에 있는 어트리뷰트 애들 어차피 이름 밸류 쌍으로 -> map으로 변환 가능하다
		BWriteDTO dto = (BWriteDTO)map.get("dto");
//		BWriteDAO dao = new BWriteDAO();
//		int result = dao.update(dto);
//		model.addAttribute("updateOk", result);
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class); /* 마이바티스가 만들어준 dao 가져와서 우리가 사용하는 것 */
	
//		model.addAttribute("updateOk", dao.update(dto)); // 매개변수가 1인거일때
		model.addAttribute("updateOk", dao.update(dto.getUid(), dto));
	}

}
