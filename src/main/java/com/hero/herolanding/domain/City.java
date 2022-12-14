package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="cityIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cityIdSequence")
	private String 도시번호;
	private String 도시명;
	private int 입국조치번호;
	private int 국가번호;
	
}
