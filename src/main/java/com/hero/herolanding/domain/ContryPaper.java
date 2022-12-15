package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="countryPapersIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class ContryPapers {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "countryPapersIdSequence")
	private int 국가별서류번호;
	private int 나이;
	private String 서류유효기간;
	private String 서류제출방법;
	private String 비고;
	private int 국가번호;
	private int 서류번호;
}
