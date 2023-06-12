package kr.or.api.mapper;

import java.util.List;

import kr.or.api.vo.ChatRoomVO;
import kr.or.api.vo.MemberVO;
import kr.or.api.vo.PaginationInfoVO;

public interface MemberMapper {

	int memSignup(MemberVO memberVO);

	int memLogin(MemberVO memberVO);

}
