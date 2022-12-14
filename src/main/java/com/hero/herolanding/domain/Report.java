package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="reportSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reportSequence")
	private Long 신고번호;
	private String 신고사유;
	private Long 신고자회원번호;
	private Long 게시글번호;
	private Long 댓글번호;

}
