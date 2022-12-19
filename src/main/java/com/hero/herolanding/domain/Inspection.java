package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "checkIdSequence", sequenceName = "check_seq", allocationSize = 1)
@Getter
@Setter
public class Inspection {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkIdSequence")
	@Column(name = "\"검사 번호\"")
	private int inspectionNum;

	@Column(name = "\"격리 기간\"")
	private int isolationPeriod;

	@Column(name = "\"검사 횟수\"")
	private int inspectionCount;

	@Column(name = "\"비고\"")
	private String inspectionNote;

//--------<@ManyToOne / Country>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"국가 번호\"")
	private Country country; // 국가 조인 컬럼

	public void setCountry(Country country) {
		this.country = country;
		country.getInspections().add(this);
	}

//--------<@ManyToOne / City>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"도시 번호\"")
	private City city; // 도시 조인 컬럼

	public void setCity(City city) {
		this.city = city;
		city.getInspections().add(this);
	}

} // Inspection class
