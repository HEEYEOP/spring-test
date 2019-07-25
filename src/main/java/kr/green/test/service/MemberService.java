package kr.green.test.service;

import org.springframework.stereotype.Service;

import kr.green.test.vo.MemberVO;


public interface MemberService {

	public MemberVO confirm(MemberVO obj);

	public void enroll(MemberVO mvo);

	public boolean dupCheck(String id);

	public String getVal(String str);

	public boolean confirm(String id, String email);

	public void sendEmail(String title, String contents, String email);

	public void modify(String id, String newPw);

	public void update(MemberVO form);

}
