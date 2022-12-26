package com.hero.herolanding.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MypageReplyDTO {
	
	// member
	private String memberNum;		// 회원번호
	private String memberId;		// 아이디
	private String memberName;		// 이름
	private String memberNickName;	// 닉네임
	
	// reply
	private String member;			// 회원번호
	private String replyNum;		// 댓글번호
	private String replyContent;	// 댓글내용
	
	
	

}
