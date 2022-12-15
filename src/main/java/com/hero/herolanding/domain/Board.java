package com.hero.herolanding.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "boardIdSequence", sequenceName = "board_seq", allocationSize = 1)
@Getter
@Setter
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardIdSequence")
	@Column(name = "게시글번호")
	private Long boardNum;

	@Column(name = "작성일자")
	private LocalDateTime insertDate;

	@Column(name = "수정일자")
	private LocalDateTime updateDate;

	@Column(name = "글제목")
	private String boardTitle;

	@Column(name = "글내용")
	private String boardContents;

	@Column(name = "조회수")
	private Long boardCount;

	@Column(name = "신고횟수")
	private Long reportCount;

	@Column(name = "대륙명")
	private String continent; // 대륙이름(ENUM)

	@Column(name = "게시글분류")
	private String boardType; // 자유게시판, 맛집후기, 여행지후기

	@Column(name = "회원번호")
	private Long memberNum; // 멤버 조인 컬럼

	@Column(name = "국가번호")
	private int countryNum; // 국가 조인 컬럼

} // Board class
