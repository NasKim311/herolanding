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
@SequenceGenerator(name = "entranceLevelIdSequence", sequenceName = "entranceLevel_seq", allocationSize = 1)
@Getter
@Setter
public class EntranceLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entranceLevelIdSequence")
	@Column(name = "입국조치번호")
	private int entranceLevelNum;

	@Column(name = "입국조치상세내용")
	private String entranceLevelDetail;

} // EntranceLevel class
