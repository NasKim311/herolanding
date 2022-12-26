package com.hero.herolanding.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminMemberDTO {
	
	private Long memberNum;
	private String memberId;
	private String memberName;
	private String memberNickName;
	private String memberPhoneNum;
	private String memberEmail;
	private boolean memberAdvAgree;
	private LocalDate signUpDate;
	private Long memberIsjoin;

}
