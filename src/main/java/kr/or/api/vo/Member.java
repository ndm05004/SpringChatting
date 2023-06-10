package kr.or.api.vo;

import java.util.Date;

import lombok.Data;


@Data
public class Member {
	private int 	memNo;
	private String	memId;
	private String	memPw;
	private String 	memName;
	private Date 	memRegdate;
	private String 	memEmail;
	
	
}
