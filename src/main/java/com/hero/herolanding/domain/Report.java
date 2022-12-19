package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "reportSequence", sequenceName = "report_seq", allocationSize = 1)
@Getter
@Setter
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSequence")
	@Column(name = "\"신고 번호\"")
	private Long reportNum;

	@Column(name = "\"신고 사유\"")
	private String reportReason;

//--------<@ManyToOne / member>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"회원 번호\"")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getReports().add(this);
	}

//--------<@ManyToOne / board>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"게시글 번호\"")
	private Board board;

	public void setBoard(Board board) {
		this.board = board;
		board.getReports().add(this);
	}

//--------<@ManyToOne / reply>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"댓글 번호\"")
	private Reply reply;

	public void setReply(Reply reply) {
		this.reply = reply;
		reply.getReports().add(this);
	}

} // Report class
