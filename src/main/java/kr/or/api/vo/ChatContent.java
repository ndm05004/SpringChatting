package kr.or.api.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ChatContent {
	private String 	chatCNo;          // CHAT_C_NO
	private String 	chatRNo;          // CHAT_R_NO
	private String 	memId;            // MEM_ID
	private Date 	chatDate;           // CHAT_DATE
	private String 	chatContent;      // CHAT_CONTENT
	private String 	chatFileName;     // CHAT_FILE_NAME
	private char 	chatFIsdeleted;     // CHAT_F_ISDELETED
	private String 	chatCCode;        // CHAT_C_CODE
	private String 	chatPtype;        // CHAT_PTYPE
}
