package kr.or.api.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.api.service.IChatService;
import kr.or.api.vo.ChatContentVO;
import kr.or.api.vo.ChatRoomVO;
import kr.or.api.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

	@Inject
	private IChatService chatService;
	
	@RequestMapping("/chatListView.do")
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
	

	@RequestMapping("/chatRNo.do")
	public String ChatRoom(@RequestBody int chatRNo) {
		log.info("ChatRoom 실행!");
		List<ChatContentVO> ccV = chatService.goInChatRoom(chatRNo);
		
		for(ChatContentVO vo : ccV) {
			System.out.println(vo.getChatContent()+"-----------------");
		}
		

		return "papge/ddit_chatRoom";
	}
	
	@RequestMapping(value = "/insertMessage.do", method = RequestMethod.POST)
	public @ResponseBody String insertMessage(String chatContent, String memId) {	
	
		ChatContentVO vo = new ChatContentVO();
		vo.setMemId(memId);
		vo.setChatContent(chatContent);
		
		int cnt =chatService.insertMessage(vo); 
		log.info(cnt+"---------------");	
		return "success";
	}
	


	
}
