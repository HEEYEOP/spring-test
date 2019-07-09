package kr.green.test.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.dao.MemberDAO;
import kr.green.test.service.MemberService;
import kr.green.test.vo.MemberVO;

@Controller
public class HomeController {
	
	@Autowired
	MemberDAO memberDao;
	
	@Autowired
	MemberService memberService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, String id) { //jsp에서 넘기는 변수명과 컨트롤러 매개변수 이름이 같아야한다.
		logger.info("Welcome home! The client locale is {}.");
		
		String email =memberDao.getEmail(id);
		System.out.println(email);
		model.addAttribute("email", email);
		
		return "home";
	}
	//로그인------------------------------------------------------------------------------------//
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String  loginGet() {
		logger.info("로그인 페이지 실행");
		return "login";
	}
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String loginPost(MemberVO obj) {
		logger.info("로그인 중");
		
		if(memberService.confirm(obj))
			return "home";
		return "/login";
	}
	
	
}
