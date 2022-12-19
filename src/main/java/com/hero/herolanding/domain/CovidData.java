package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class CovidData {

	@Column(name = "\"총 확진자 수\"")
	private String totalCovidCount;

	@Column(name = "\"신규 확진자 수(1일)\"")
	private String newCovidCount1;

	@Column(name = "\"신규 확진자 수(60일)\"")
	private String newCovidCount60;

	@Column(name = "\"백만명당 확진자 수\"")
	private String milionCount;

	@Column(name = "\"사망\"")
	private String samang;

	protected CovidData() {
	}

	public CovidData(String totalCovidCount, String newCovidCount1, String newCovidCount60, String milionCount,
			String samang) {
		super();
		this.totalCovidCount = totalCovidCount;
		this.newCovidCount1 = newCovidCount1;
		this.newCovidCount60 = newCovidCount60;
		this.milionCount = milionCount;
		this.samang = samang;
	}

} // CovidData Embeddable
