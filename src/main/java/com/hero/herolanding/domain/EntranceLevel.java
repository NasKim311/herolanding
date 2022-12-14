package com.hero.herolanding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name="entranceLevelIdSequence" , sequenceName = "member_seq" , allocationSize = 1)
@Getter @Setter
public class EntranceLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "entranceLevelIdSequence")
	private int 입국조치번호;
	private String 입국조치상세;
	
}
