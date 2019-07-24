package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
