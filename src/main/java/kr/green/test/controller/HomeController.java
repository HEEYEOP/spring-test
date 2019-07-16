package kr.green.test.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.test.dao.MemberDAO;
import kr.green.test.service.MemberService;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Controller
public class HomeController {
	
	@Autowired
	MemberDAO memberDao;
	
	@Autowired
	MemberService memberService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) { 
		logger.info("메인페이지 로그인화면");
		
		return "home";
	}
	
	
	//로그인------------------------------------------------------------------------------------//
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String  loginGet() {
		logger.info("로그인 페이지 실행");
		return "login";
	}
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public String loginPost(Model model, MemberVO obj) {
		logger.info("로그인 중");
		
		MemberVO mVO = memberService.confirm(obj); 
		System.out.println(mVO);
		if( mVO != null) {
			System.out.println("로그인 성공");
			model.addAttribute("user", mVO);
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			return "redirect:/";
		}
	}
	
	@RequestMapping(value ="/logout", method = RequestMethod.GET)
	public String signoutPost(HttpServletRequest r) {
		logger.info("로그아웃");
		r.getSession().removeAttribute("user");
		return "redirect:/";
	}
	
	
}
