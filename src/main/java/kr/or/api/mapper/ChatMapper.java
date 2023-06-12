package kr.or.api.mapper;

import java.util.List;

import kr.or.api.vo.ChatContentVO;
import kr.or.api.vo.ChatRoomVO;
import kr.or.api.vo.PaginationInfoVO;

public interface ChatMapper {

	int insertMessage(ChatContentVO chatContent);

	int selectChatRoomCount(PaginationInfoVO<ChatRoomVO> pagingVO);

	List<ChatRoomVO> selectChatRoomList(PaginationInfoVO<ChatRoomVO> pagingVO);

}
