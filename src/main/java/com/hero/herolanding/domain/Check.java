package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="checkIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Check {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "checkIdSequence")
	private int 검사번호;
	private int 격리기간;
	private int 검사횟수;
	private String 비고;
	private int 국가번호;
	private int 도시번호;
	
}
