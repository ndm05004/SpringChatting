package kr.or.api.vo;

import lombok.Data;

@Data
public class ChatRoomVO {

	private int chatRNo;       // CHAT_R_NO
	private String memId;         // MEM_ID
	private String chatRTitle;    // CHAT_R_TITLE
	private String chatRIsopen;   // CHAT_R_ISOPEN
}
