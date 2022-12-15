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
@SequenceGenerator(name = "memberIdSequence", sequenceName = "member_seq", allocationSize = 1)
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberIdSequence")
	@Column(name = "회원번호")
	private Long memberNum;

	@Column(name = "아이디")
	private String memberId;

	@Column(name = "비밀번호")
	private String memberPw;

	@Column(name = "이름")
	private String memberName;

	@Column(name = "닉네임")
	private String memberNickname;

	@Column(name = "핸드폰번호")
	private String memberPhoneNum;

	@Column(name = "이메일")
	private String memberEmail;

	@Column(name = "이용약관동의")
	private boolean memberUsingAgree;

	@Column(name = "개인정보약관동의")
	private boolean memberDataAgree;

	@Column(name = "광고약관동의")
	private boolean memberAdvAgree;

	@Column(name = "회원상태")
	private boolean memberIsjoin;

	@Column(name = "국가번호")
	private Long countryNum; // 국가테이블 조인 컬럼

} // Member class
