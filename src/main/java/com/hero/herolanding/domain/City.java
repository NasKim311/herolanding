package com.hero.herolanding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
	@Enumerated(EnumType.STRING)
	private EntranceLevel entranceLevel;

//--------<@ManyToOne / Country>-------------------------------------------------------------------------------------	
	@ManyToOne
	@JoinColumn(name = "국가번호")
	private Country country;

//--------<@OneToMany / Check>-------------------------------------------------------------------------------------	
	@OneToMany
	private List<Check> checks = new ArrayList<Check>();

	public void setCountry(Country country) {
		this.country = country;
		country.getCities().add(this);
	}

} // City class
