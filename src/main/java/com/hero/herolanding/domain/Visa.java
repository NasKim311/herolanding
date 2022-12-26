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
@SequenceGenerator(name = "visaIdSequence", sequenceName = "visa_seq", allocationSize = 1)
@Getter
@Setter
public class Visa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visaIdSequence")
	@Column(name = "\"비자 번호\"")
	private int visaNum;

	@Column(name = "\"입국시 비자 소지 여부\"")
	private String entranceVisaStatus;

	@Column(name = "\"일반여권 소지자 입국 가능 여부\"")
	private String normalPassportStatus;

	@Column(name = "\"일반여권 소지자 입국 가능 기간\"")
	private String normalPassportPeriod;

	@Column(name = "\"관용여권 소지자 입국 가능 여부\"")
	private String officialPassportStatus;

	@Column(name = "\"관용여권 소지자 입국 가능 기간\"")
	private String officialPassportPeriod;

	@Column(name = "\"외교관여권 소지자 입국 가능 여부\"")
	private String diplomatPassportStatus;

	@Column(name = "\"외교관여권 소지자 입국 가능 기간\"")
	private String diplomatPassportPeriod;

	@Column(name = "\"무비자 입국 근거\"")
	private String reasonForVisaFree;

	@Column(name = "\"비고\"")
	private String visaNote;

//--------<@ManyToOne / Country>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "\"국가 번호\"")
	private Country country;

	public void setCountry(Country country) {
		this.country = country;
		country.getVisas().add(this);
	}

} // Visa class
