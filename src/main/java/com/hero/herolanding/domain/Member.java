package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="memberIdSequence", sequenceName = "member_seq", allocationSize = 1)
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberIdSequence")
	private Long 아이디;
	private String 비밀번호;
	private String 닉네임;
	private String 핸드폰번호;
	private String 이메일;
	private boolean 약관동의;
	private boolean 상태;
	
}
