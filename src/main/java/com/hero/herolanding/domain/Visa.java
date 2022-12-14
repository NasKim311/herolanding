package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="visaIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Visa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "visaIdSequence")
	private int 비자번호;
	private int 국가번호;
	private boolean 입국시소지여부;
	private boolean 일반여권소지자_입국가능여부;
	private String 일반여권소지자_입국가능기간;
	private boolean 관용여권소지자_입국가능여부;
	private String 관용여권소지자_입국가능기간;
	private boolean 외교관여권소지자_입국가능여부;
	private String 외교관여권소지자_입국가능기간;
	private String 무비자입국근거;
	private String 비고;
	
}
