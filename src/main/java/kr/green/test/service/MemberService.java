package kr.green.test.service;

import org.springframework.stereotype.Service;

import kr.green.test.vo.MemberVO;


public interface MemberService {

	public MemberVO confirm(MemberVO obj);

	public void enroll(MemberVO mvo);

}
