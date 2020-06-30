package com.lec.sts11_param;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lec.beans.WriteDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// parameter 추출
	//hander 메소드에서도 서블릿에서 보았던 HttpServletRequest, HttpServletResponse 매개변수 가능
	
	@RequestMapping(value = "/member/delete", method =RequestMethod.GET) // -> /member/delete?id=34
//	public String delMember(Model model, HttpServletRequest request) { // 매개변수 순서 관게없음 autowired 랑 비슷하다고 볼수있다, SpringConroller 이기 떄문에 가능하다고 생각하면 된다?.
// 순서 바꾸어도 동작
		public String delMember( HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		model.addAttribute("mbId",id);
		return "member/delete";
	}
	
	@RequestMapping(value = "/member/regOk", method = RequestMethod.POST) // 405 에러 발생 get 방식으로는 접근할 수 없다
	public String regOkMember(){
		System.out.println("/member/regOk : POST");
		return "member/regOk";
	}
	@RequestMapping(value = "/member/regOk", method = RequestMethod.GET)
	public String regOkMember(Model model ){
		System.out.println("/member/regOk : GET");
		return "member/regOk";
	}
	
	@RequestMapping(value = "/member/regist")
	public String registMemeber() {
		return "member/regist";
	}
	
	// GET/ POST  둘다 받는 handler
	@RequestMapping(value = "/member/regOk2", method = {RequestMethod.GET, RequestMethod.POST})
	public String regOkMember2() {
		return "member/regOk";
	}
	
	// handler에 request parameter 의 name 값과 '같은 이름의 매개변수' 가 있으면
	// 바로 그 매개변수가 request parameter 값을 받아온다. (내부적으로 스프링이 알아서 주입하는 셈이다)
	//@RequestMapping(value = "/member/find")
	// 매개변수의 순서는 중요하지 않다
//	public String findMember(String id, String name, Model model) {
//	public String findMember( String name, Model model,String id) {
//		model.addAttribute("id",id);
		
	// 숫자 타입은 바로 parsing 하여 받는다  double 타입이면 0.0
	// id 에 문자를 넣으면 400 에러가 뜬게된다.
	// 동일한 파라메터들이 여러개이면 첫번째꺼만 들어가게된다
//	public String findMember( String name, Model model, int id) {
		//	model.addAttribute("id",id * 2);
		//	model.addAttribute("name",name);
		
/*		// 동일한 name 의 request parameter들 (복수개)
	public String findMember( Model model, String[] name, int[] id) {
		model.addAttribute("id",Arrays.toString(id));
		model.addAttribute("name",Arrays.toString(name));
		return "member/find";
	}
	*/
	
	//@RequestParam 사용
	@RequestMapping("/member/find")
	public String findMember(  Model model,
			@RequestParam("name") String username,
			@RequestParam("id") String userid) {
		
		model.addAttribute("id",userid);
		model.addAttribute("name",username);
		
		return "member/find";
	}
	
	
	//--------------------
	// 커맨드 객체 (Command Object) 사용 ( DTO 객체)
	
	@RequestMapping("/board/write")
	public String writeBoard() {
		return "board/write";
	}
	
	// 기존방식으로 구현하기
	// 매  parameter들을 매개변수화? 해야한다. 힘듬
	@RequestMapping(value = "/board/writeOk", method = RequestMethod.POST)
	/* 
	public String writeOkBoard(Model model,
			@RequestParam("name") String name,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content
			) {
		WriteDTO dto = new WriteDTO();
		dto.setName(name);
		dto.setSubject(subject);
		dto.setContent(content);
		
		model.addAttribute("dto", dto);
		
		return "board/writeOk";
	}
	*/
	
	// 커맨드 객체 사용
	// 코드 작업량이 매우 줄어든다  
	// getter/ setter 이름이 중요하당
	
	// 커맨드 객체 사용
//	public String writeOkBoard(WriteDTO dto) {
	
	// 커맨드 객체 attribute id 변경
	public String writeOkBoard(
			@ModelAttribute("DTO") WriteDTO dto) {
		
		System.out.println(dto);
		return "board/writeOk";
	}
	
	// @PathVariable
	@RequestMapping("/board/writePath/{name}/{subject}/{content}")
	public String writePathBoard(Model model,
			@PathVariable String name,
			@PathVariable String subject,
			@PathVariable String content) {
		model.addAttribute("name",name);
		model.addAttribute("subject",subject);
		model.addAttribute("content",content);
		return "board/writepath";
	}
	
	//46
	/*
	@RequestMapping("/member/ageCheck")
	public String chkAge(int age) { // 매개변수
		if(age< 19) {
			return "redirect:/member/underAge";
		}else {
			return "redirect:/member/adult";			
		}
	}
	
	@RequestMapping("member/underAge")
	public String pageUnderAge() {
		return "member/ageUnder";
	}
	@RequestMapping("member/adult")
	public String pageAdult() {
		return "member/ageAdult";
	}
	
	@RequestMapping(value = "/common") 
	public String cccmmm() { 			
		return "comn"; 
	}
	*/
	
	
	@RequestMapping("/member/ageCheck")
	public String chkAge(int age, RedirectAttributes redirectAttr) { // 매개변수
		redirectAttr.addAttribute("age",age);
		if(age< 19) {
			return "redirect:/member/underAge";
		}else {
			return "redirect:/member/adult";			
		}
	}
	
	@RequestMapping("member/underAge")
	public String pageUnderAge(
			@RequestParam("age") int age, Model model
			) {
		model.addAttribute("age",age);
		return "member/ageUnder";
	}
	@RequestMapping("member/adult")
	public String pageAdult(	@RequestParam("age") int age, Model model) {
		model.addAttribute("age",age);
		return "member/ageAdult";
	}
	
	@RequestMapping(value = "/common") 
	public String cccmmm() { 			
		return "comn"; 
	}
	
	
	
}















