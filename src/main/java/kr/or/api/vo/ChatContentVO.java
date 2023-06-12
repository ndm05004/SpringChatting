package kr.or.api.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ChatContentVO {
	private String 	chatCNo;          // CHAT_C_NO
	private int 	chatRNo;          // CHAT_R_NO
	private String 	memId;            // MEM_ID
	private Date 	chatDate;           // CHAT_DATE
	private String 	chatContent;      // CHAT_CONTENT
	
	// 파일 전송 가능 할 시 
	private String 	chatFileName;     // CHAT_FILE_NAME
	private char 	chatFIsdeleted;     // CHAT_F_ISDELETED
	private String 	chatCCode;        // CHAT_C_CODE
	private String 	chatPtype;        // CHAT_PTYPE
}
