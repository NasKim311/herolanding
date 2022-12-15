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
@SequenceGenerator(name = "countryPapersIdSequence", sequenceName = "countryPaper_seq", allocationSize = 1)
@Getter
@Setter
public class ContryPaper {

	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "countryPapersIdSequence")
	private int 국가별서류번호;
	private int 나이;
	private String 서류유효기간;
	private String 서류제출방법;
	private String 비고;
	private int 국가번호;
	private int 서류번호;
}
=======
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countryPapersIdSequence")
	@Column(name = "국가별서류번호")
	private int countryPaperNum;
	
	@Column(name = "최소나이")
	private int countryPaperMinAge;
	
	@Column(name = "서류유효기간")
	private String countryPaperExpiration;
	
	@Column(name = "서류제출방법")
	private String countryPaperSubmitType;
	
	@Column(name = "비고")
	private String countryPaperNote;
	
	@Column(name = "국가번호")
	private int countryNum; // 국가 조인 컬럼
	
	@Column(name = "서류번호")
	private int paperNum; // 서류 조인 컬럼

} // ContryPapers class
>>>>>>> hero/main
