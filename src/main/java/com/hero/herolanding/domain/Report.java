package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "신고번호")
	private Long reportNum;

	@Column(name = "신고사유")
	private String reportReason;

	@Column(name = "신고자회원번호")
	private Long reportMemberNum;

	@Column(name = "게시글번호")
	private Long boardNum; // 게시글 조인 컬럼

	@Column(name = "댓글번호")
	private Long replyNum; // 댓글 조인 컬럼

} // Report class
