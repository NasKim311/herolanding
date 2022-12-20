package com.hero.herolanding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	@Column(name = "\"게시글 번호\"")
	private Long boardNum;

	@Column(name = "\"작성 일자\"")
	private String insertDate;

	@Column(name = "\"수정 일자\"")
	private String updateDate;

	@Column(name = "\"글 제목\"")
	private String boardTitle;

	@Column(name = "\"글 내용\"")
	private String boardContents;

	@Column(name = "\"조회 수\"")
	private Long boardCount;

	@Column(name = "\"신고 수\"")
	private Long reportCount;

	@Enumerated(EnumType.STRING)
	@Column(name = "\"대륙 명\"")
	private Continent continent;

	@Enumerated(EnumType.STRING)
	@Column(name = "\"게시글 분류\"")
	private BoardType boardType;

//--------<@ManyToOne / member>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"회원 번호\"")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getBoards().add(this);
	}

//--------<@OneToMany / reply>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "board" , orphanRemoval = true  ,cascade = CascadeType.PERSIST )
	private List<Reply> replys = new ArrayList<Reply>();

//--------<@OneToMany / reply>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "board" ,cascade = CascadeType.PERSIST , orphanRemoval = true )
	private List<Report> reports = new ArrayList<Report>();
	
	  public Board updateViewCount(long boardCount)
	   {
		   this.boardCount = boardCount + 1;
		   return this;
	   } // 조회수를 올리기위한 로직

} // Board class
