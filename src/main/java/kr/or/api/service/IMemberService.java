package kr.or.api.service;

import kr.or.api.vo.MemberVO;

public interface IMemberService {
	public int memSignup(MemberVO memberVO);
	
	public int memLogin(MemberVO memberVO);
}
