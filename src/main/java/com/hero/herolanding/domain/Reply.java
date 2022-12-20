package com.hero.herolanding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@SequenceGenerator(name = "replyIdSequence", sequenceName = "reply_seq", allocationSize = 1)
@Getter
@Setter
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "replyIdSequence")
	@Column(name = "\"댓글 번호\"")
	private Long replyNum;
	
	@Column(name = "\"부모 댓글 번호\"")
	private Long ParentReplyNum;

	@Column(name = "\"작성 일자\"")
	private String replyInsertDate;

	@Column(name = "\"수정 일자\"")
	private String replyUpdateDate;

	@Column(name = "\"뎁스 레벨\"")
	private int replyDepthLevel;

	@Column(name = "\"댓글 내용\"")
	private String replyContent;

//--------<@ManyToOne / member>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"회원 번호\"")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getReplys().add(this);
	}

//--------<@ManyToOne / board>-------------------------------------------------------------------------------------	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "\"게시글 번호\"" )
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getReplys().add(this);
	}

//--------<@OneToMany / report>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "reply")
	private List<Report> reports = new ArrayList<Report>();

} // Reply class
