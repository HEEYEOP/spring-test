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

@Controller
public class HomeController {
	
	@Autowired
	MemberDAO memberDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, String id) { //jsp에서 넘기는 변수명과 컨트롤러 매개변수 이름이 같아야한다.
		logger.info("Welcome home! The client locale is {}.");
		
		String email =memberDao.getEmail(id);
		System.out.println(email);
		
		model.addAttribute("email", email);
		
		
		return "home";
	}
}
