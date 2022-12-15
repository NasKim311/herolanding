package com.hero.herolanding.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "댓글번호")
	private Long replyNum;

	@Column(name = "작성일자")
	private LocalDateTime replyInsertDate;

	@Column(name = "수정일자")
	private LocalDateTime replyUpdateDate;

	@Column(name = "뎁스레벨")
	private int replyDepthLevel;

//--------<@ManyToOne / member>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "회원번호")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getReplys().add(this);
	}

//--------<@ManyToOne / board>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "게시글번호")
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getReplys().add(this);
	}

//--------<@OneToMany / report>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "reply")
	private List<Report> reports = new ArrayList<Report>();

} // Reply class
