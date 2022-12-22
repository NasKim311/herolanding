package com.hero.herolanding.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemberDTO {

	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickName;
	private String memberEmail;
	private String memberPhoneNum;
	
} // UpdateMemberDTO class
