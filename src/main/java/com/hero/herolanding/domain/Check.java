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
@SequenceGenerator(name = "checkIdSequence", sequenceName = "check_seq", allocationSize = 1)
@Getter
@Setter
public class Check {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkIdSequence")
	@Column(name = "검사번호")
	private int checkNum;

	@Column(name = "격리기간")
	private int isolationPeriod;

	@Column(name = "검사횟수")
	private int checkCount;

	@Column(name = "비고")
	private String checkNote;

	@Column(name = "국가번호")
	private Long countryNum; // 국가 조인 컬럼

	@Column(name = "도시번호")
	private Long cityNum; // 도시 조인 컬럼

} // Check class
