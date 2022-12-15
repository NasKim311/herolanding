package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class CovidData {

	@Column(name = "총확진자수")
	private String totalCovidCount;

	@Column(name = "신규확진자수")
	private String newCovidCount;

	@Column(name = "사망")
	private String samang;

	@Column(name = "총접종횟수")
	private String totalInjectionCount;

	@Column(name = "신규접종횟수")
	private String newInjectionCount;

	@Column(name = "백신접종을완료한사람의수")
	private String injectionCompleteCount;

	protected CovidData() {
	}

	public CovidData(String totalCovidCount, String newCovidCount, String samang, String totalInjectionCount,
			String newInjectionCount, String injectionCompleteCount) {
		super();
		this.totalCovidCount = totalCovidCount;
		this.newCovidCount = newCovidCount;
		this.samang = samang;
		this.totalInjectionCount = totalInjectionCount;
		this.newInjectionCount = newInjectionCount;
		this.injectionCompleteCount = injectionCompleteCount;
	}

} // CovidData Embeddable
