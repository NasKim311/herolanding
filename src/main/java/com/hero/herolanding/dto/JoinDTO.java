package com.hero.herolanding.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {

	private Long memberNum;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberNickName;
	private String memberEmail;
	private String memberPhoneNum;
	private Boolean memberUsingAgree;
	private Boolean memberDataAgree;
	private Boolean memberAdvAgree;
	private LocalDate signUpDate; 
	
	
	
	
	
	
} // name 안쓰나?
