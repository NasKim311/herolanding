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
@SequenceGenerator(name="boardIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "boardIdSequence")
	private Long 게시글번호;
	private LocalDateTime 작성일자;
	private LocalDateTime 수정일자;
	private String 글제목;
	private String 글내용;
	private Long 조회수;
	private Long 신고횟수;
	private String 지역;
	private String 게시글분류;
	private String 닉네임;
	private Long 회원번호;
	private int 국가번호;
	
}







