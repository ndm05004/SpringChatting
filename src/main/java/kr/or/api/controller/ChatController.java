package kr.or.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.api.service.IChatService;
import kr.or.api.vo.ChatRoomVO;
import kr.or.api.vo.PaginationInfoVO;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Inject
	private IChatService chatService;
	
	@RequestMapping("/chatList")
	public String chatList(
		@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
		@RequestParam(required = false, defaultValue = "title") String searchType,
		@RequestParam(required = false) String searchWord,
		Model model) {
	
	PaginationInfoVO<ChatRoomVO> pagingVO = new PaginationInfoVO<ChatRoomVO>();
	
	if(StringUtils.isNotBlank(searchWord)) {
		pagingVO.setSearchType(searchType);
		pagingVO.setSearchWord(searchWord);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchWord", searchWord);
	}
	
	pagingVO.setCurrentPage(currentPage);
	
	int totalRecord = chatService.selectChatRoomCount(pagingVO);
	pagingVO.setTotalRecord(totalRecord);
	

	List<ChatRoomVO> dataList = chatService.selectChatRoomList(pagingVO);
	pagingVO.setDataList(dataList);
	model.addAttribute("pagingVO", pagingVO);
	
	return "pages/ddit_list";
	}
	
	@RequestMapping("/chatRNo")
	public String ChatRoom(int chatRNo) {
		chatService.goInChatRoom(chatRNo);
		
		return "papge/ddit_chatRoom";
	}
	
	
	
}
