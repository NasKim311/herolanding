package com.hero.herolanding.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="replyIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Reply {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "replyIdSequence")
	private Long 댓글번호;
	private LocalDateTime 작성일자;
	private LocalDateTime 수정일자;
	private int 뎁스레벨;
	private Long 회원번호;
	private String 닉네임;
	private Long 게시글번호;
	
}
