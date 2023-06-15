package kr.or.api.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.api.mapper.ChatMapper;
import kr.or.api.mapper.MemberMapper;
import kr.or.api.vo.ChatContentVO;
import kr.or.api.vo.ChatRoomVO;
import kr.or.api.vo.PaginationInfoVO;

@Service
public class ChatServiceImpl implements IChatService {
	
	@Inject
	private ChatMapper chatMapper;
	
	
	@Override
	public int insertMessage(ChatContentVO chatContent) {
		return chatMapper.insertMessage(chatContent);
	}


	@Override
	public int selectChatRoomCount(PaginationInfoVO<ChatRoomVO> pagingVO) {
		return chatMapper.selectChatRoomCount(pagingVO);
	}


	@Override
	public List<ChatRoomVO> selectChatRoomList(PaginationInfoVO<ChatRoomVO> pagingVO) {
		return chatMapper.selectChatRoomList(pagingVO);
	}


	@Override
	public List<ChatContentVO> goInChatRoom(int chatRNo) {
		return chatMapper.goInChatRoom(chatRNo);
	}
	
	


}
