package com.hero.herolanding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "countryIdSequence", sequenceName = "country_seq", allocationSize = 1)
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
	@Enumerated(EnumType.STRING)
	private String continent;

	@Column(name = "대사관영사관링크")
	private String embassyLink;

	@Column(name = "비고")
	private String countryNote;

	@Embedded
	private String covidData;

//--------<@ManyToOne / ExchangeRate>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "통화번호")
	private ExchangeRate exchangeRate;

	public void setExchangeRate(ExchangeRate exchangeRate) {
		this.exchangeRate = exchangeRate;
		exchangeRate.getCountries().add(this);
	}

//--------<@OneToMany / Inspection>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "country")
	private List<Inspection> inspections = new ArrayList<Inspection>();

//--------<@OneToMany / Visa>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "country")
	private List<Visa> visas = new ArrayList<Visa>();

//--------<@OneToMany / CountryPaper>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "country")
	private List<CountryPaper> countryPapers = new ArrayList<CountryPaper>();

//--------<@OneToMany / City>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "country")
	private List<City> cities = new ArrayList<City>();

} // Country class
