package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="papersIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Papers {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "papersIdSequence")
	private int 서류번호;
	private String 서류명;
	private String 발급기관;
	private String 링크;
	private String 비고;
}
