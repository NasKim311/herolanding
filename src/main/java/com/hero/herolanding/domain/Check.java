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
public class Check {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkIdSequence")
	@Column(name = "검사번호")
	private int checkNum;

	@Column(name = "격리기간")
	private int isolationPeriod;

	@Column(name = "검사횟수")
	private int checkCount;

	@Column(name = "비고")
	private String checkNote;

//--------<@ManyToOne / Country>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "국가번호")
	private Country country; // 국가 조인 컬럼

	public void setCountry(Country country) {
		this.country = country;
		country.getChecks().add(this);
	}

//--------<@ManyToOne / City>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "도시번호")
	private City city; // 도시 조인 컬럼

	public void setCity(City city) {
		this.city = city;
		city.getChecks().add(this);
	}

} // Check class
