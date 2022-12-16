package com.hero.herolanding.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "대륙명")
	private String continent;

	@Enumerated(EnumType.STRING)
	@Column(name = "게시글분류")
	private String boardType;

//--------<@ManyToOne / member>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "회원번호")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getBoards().add(this);
	}

//--------<@OneToMany / reply>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "board")
	private List<Reply> replys = new ArrayList<Reply>();

//--------<@OneToMany / reply>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "board")
	private List<Report> reports = new ArrayList<Report>();

} // Board class
