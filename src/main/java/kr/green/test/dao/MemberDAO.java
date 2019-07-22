package kr.green.test.dao;
import org.apache.ibatis.annotations.Param;

import kr.green.test.vo.MemberVO;


public interface MemberDAO {
	public String getEmail(String id);

	public MemberVO getMember(MemberVO obj);

	public void insertMember(MemberVO mvo);
	

}
