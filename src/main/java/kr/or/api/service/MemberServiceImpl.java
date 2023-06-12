package kr.or.api.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.api.mapper.MemberMapper;
import kr.or.api.vo.MemberVO;


@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	private MemberMapper memberMapper;
	
	@Override
	public int memSignup(MemberVO memberVO) {
		
		return memberMapper.memSignup(memberVO);
	}

	@Override
	public int memLogin(MemberVO memberVO) {
				
		return memberMapper.memLogin(memberVO);
	}

}
