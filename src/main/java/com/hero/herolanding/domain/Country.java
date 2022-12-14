package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="countryIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "countryIdSequence")
	private Long 국가번호;
	private String 국가명;
	private String 입국조치;
	private String 대사관_영사관;
	private String 비고;
	
}
