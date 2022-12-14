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
@SequenceGenerator(name = "replyIdSequence", sequenceName = "member_seq", allocationSize = 1)
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

	@Column(name = "회원번호")
	private Long memberNum; // 멤버 조인 컬럼

	@Column(name = "게시글번호")
	private Long boardNum; // 게시글 조인 컬럼

} // Reply class
