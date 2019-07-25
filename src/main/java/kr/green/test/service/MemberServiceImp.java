package kr.green.test.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDAO memberDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Override
	public MemberVO confirm(MemberVO obj) {
		if(obj == null)
			return null;
		MemberVO dbValue = memberDao.getMember(obj);
		if(dbValue == null) 
			return null;
		
		if(passwordEncoder.matches(obj.getPw(), dbValue.getPw()))
			return dbValue;
		return null;
	} 

	@Override
	public void enroll(MemberVO mvo) {
		if(mvo == null) return ;
		
		String encPw = passwordEncoder.encode(mvo.getPw());
	    mvo.setPw(encPw);
	    
	    memberDao.insertMember(mvo);
	   
	}

	@Override
	public boolean dupCheck(String id) {
		MemberVO mVO = new MemberVO();
		mVO.setId(id);
		if(memberDao.getMember(mVO) != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getVal(String str) {
		String [] tmpStr = str.split("=");
		if(tmpStr.length != 2)
			return null;
		return tmpStr[1];
	}

	@Override
	public boolean confirm(String id, String email) {
		MemberVO obj = new MemberVO();
		obj.setId(id);
		
		MemberVO user = memberDao.getMember(obj);
		if(user != null && user.getEmail().equals(email))
			return true;
		return false;
	}

	@Override
	public void sendEmail(String title, String contents, String email) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	        
	        String setfrom ="yjihyo1321@gmail.com";

	        messageHelper.setFrom(setfrom);  
	        messageHelper.setTo(email);     
	        messageHelper.setSubject(title); 
	        messageHelper.setText(contents); 

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }
		
	}

	@Override
	public void modify(String id, String newPw) {
		MemberVO obj = new MemberVO();
		obj.setId(id);
		MemberVO user = memberDao.getMember(obj);
		if(user == null)
			return;
		String encPw = passwordEncoder.encode(newPw);
		user.setPw(encPw);
		memberDao.modify(user);
			
		
	}

	@Override
	public void update(MemberVO form) {
		
		MemberVO user = memberDao.getMember(form);
		
		if(form.getName() == null) {
			form.setName(user.getName());
		}
		if(form.getEmail() == null) {
			form.setEmail(user.getEmail());
		}
		if(form.getPw() == null || form.getPw().length() == 0) {
			form.setPw(user.getPw());
		}
		if(form.getGender() == null) {
			form.setGender(user.getGender());
		}
		
		memberDao.modify(form);
		
	}

}
