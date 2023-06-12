package kr.or.api.service;

import java.util.List;

import kr.or.api.vo.ChatContentVO;
import kr.or.api.vo.ChatRoomVO;
import kr.or.api.vo.PaginationInfoVO;

public interface IChatService {

	int insertMessage(ChatContentVO chatContent);

	int selectChatRoomCount(PaginationInfoVO<ChatRoomVO> pagingVO);

	List<ChatRoomVO> selectChatRoomList(PaginationInfoVO<ChatRoomVO> pagingVO);

	void goInChatRoom(int chatRNo);


}
