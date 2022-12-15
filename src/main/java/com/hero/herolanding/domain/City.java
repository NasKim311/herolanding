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
@SequenceGenerator(name = "cityIdSequence", sequenceName = "city_seq", allocationSize = 1)
@Getter
@Setter
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cityIdSequence")
	@Column(name = "도시번호")
	private Long cityNum;

	@Column(name = "도시명")
	private String cityName;

	@Column(name = "입국조치번호")
	private int entranceLevelNum; // 입국조치 조인 컬럼

	@Column(name = "국가번호")
	private Long countryNum; // 국가 조인 컬럼

} // City class
