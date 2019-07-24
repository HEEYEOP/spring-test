package kr.green.test.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//----------------7/22월 복습-----------------------------------
	@RequestMapping(value="/joinMembership", method= RequestMethod.GET)
	public String joinMembershipGet() {
		logger.info("회원가입페이지 실행");

		return "joinMembership";
	}
	
	@RequestMapping(value="/joinMembership", method= RequestMethod.POST)
	public String joinMembershipPost(MemberVO mvo) {
		logger.info("회원가입  진행중");

		memberService.enroll(mvo);
		
		return "redirect:/";
	}
	
	//-----------------7/24(수)복습---------------------------
	@RequestMapping(value="/dupCheck")
	@ResponseBody
	public boolean idcheck(@RequestBody String id, Model model){
	    
	    if(memberService.dupCheck(id)) {
    		model.addAttribute("checking", true);
	    	return true;	    	
	    }else {
    		model.addAttribute("checking", false);
		    return false;
	    }
	   
	}
	
	
	
	
}
