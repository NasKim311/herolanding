package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Report {
	
	@Id
	private String 신고사유;
	private Long 신고자회원번호;
	private Long 게시글번호;
	private Long 댓글번호;

}
