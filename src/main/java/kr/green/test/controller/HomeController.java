package kr.green.test.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	
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
    		model.addAttribute("checking", true); //----이거는 모델에 담아보내줘서 jsp에서 받는게 아니고 그냥 return 값만 써주면 알아서 jsp에서 받음
	    	return true;	    	
	    }else {
    		model.addAttribute("checking", false);
		    return false; //--------------------------------------이거 안되는건가?
	    }  
	}
	
	//--------------------------------------------------------------------
	
	
	@RequestMapping(value="/sendingPw", method = RequestMethod.GET)
	public String sendingPwGet() {
		logger.info("비밀번호찾기 페이지 실행");
		return "sendingPw";
	}
	
	
	
	
	
	@RequestMapping(value="/ajaxCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> ajaxCheckPost(@RequestBody String idANDemail) {
		logger.info("비밀번호찾기_아이디,이메일체크");
		
		Map<Object,	Object> map = new HashMap<Object, Object>();
		 
		try {
			idANDemail = URLDecoder.decode(idANDemail, "UTF-8");
			//System.out.println(idANDemail);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String [] tmpStr = idANDemail.split("&");
		String id = memberService.getVal(tmpStr[0]);
		String email = memberService.getVal(tmpStr[1]);
		
		boolean isMember = memberService.confirm(id,email);
		map.put("isMember", isMember);
		return map;
		
	}
	
	@RequestMapping(value="/password/send", method = RequestMethod.POST)
	public String passwordSendPost(String id, String email) {
		logger.info("비밀번호 생성하며 메일보내기");
		
		String str ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		String newPw = "";
		
		while(newPw.length() < 8) {
			int index = (int)(Math.random()*str.length());
			newPw += str.charAt(index);
		}
		String title = "변경된 비밀번호입니다";
		String contents ="새 비밀번호 :"+ newPw;
		memberService.sendEmail(title,contents,email);
		//-------------//
		
		memberService.modify(id,newPw);
		return "redirect:/complete";
	}
	
	@RequestMapping(value="/complete", method = RequestMethod.GET)
	public String completeGet() {	
		return "complete";
	}
	//------------회원 정보수정
	@RequestMapping(value="/memberModify", method = RequestMethod.GET)
	public String memberModifyGet(Boolean success, Model model) { //꼭 래퍼클래스 Boolean을 써줘야 한다. 왜?null값도 저장할 수 있어야하니까. 왜? 처음에 회원수정페이지에 들어왔을 경우 success에는null값이 있을테니까
		logger.info("회원정보 수정 페이지 실행");
		model.addAttribute("success", success);
		
		return "memberModify";
	}
	
	@RequestMapping(value="/memberModify", method = RequestMethod.POST)
	public String memberModifyPost(MemberVO form, HttpServletRequest r, Model model) {
		logger.info("회원정보 수정중");
		
		System.out.println(form); //업데이트하려고하는 정보를 담고 있는 form
		
		MemberVO okUpdate = memberService.update(form);
		boolean t = memberService.updateSession(r,okUpdate);
		model.addAttribute("success", t);
		
		return "redirect:/memberModify";
	}
	
	
	
	
	
	
}
