package kr.green.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public boolean confrim(MemberVO obj) {
		if(obj == null)
			return false;
		MemberVO dbValue = memberDao.getMember(obj);
		System.out.println(dbValue);
		if(dbValue == null) 
			return false;
		
		if(obj.getPw().equals(dbValue.getPw()))
			return true;
		return false;
	}

}
