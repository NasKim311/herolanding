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
@SequenceGenerator(name = "countryIdSequence", sequenceName = "member_seq", allocationSize = 1)
@Getter
@Setter
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countryIdSequence")
	@Column(name = "국가번호")
	private Long countryNum;

	@Column(name = "국가명")
	private String countryName;

	@Column(name = "대륙명")
	private String continent; // 대륙이름(ENUM)

	@Column(name = "대사관/영사관링크")
	private String embassyLink; // 해당 대사관/영사관 링크

	@Column(name = "비고")
	private String countryNote;

	@Column(name = "입국조치번호")
	private int entranceLevelNum; // 입국조치 조인 컬럼

} // Country class
