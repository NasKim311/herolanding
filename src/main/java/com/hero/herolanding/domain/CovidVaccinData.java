package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
public class CovidVaccinData {
	

	@Column(name = "총접종횟수")
	private String totalInjectionCount;

	@Column(name = "신규접종횟수1일")
	private String newInjectionCount1;
	
	@Column(name = "신규접종횟수60일")
	private String newInjectionCount60;

	@Column(name = "백신접종을완료한사람의수")
	private String injectionCompleteCount;
	
	@Column(name = "인구중접종완료자비율")
	private String injectionCompletePercent;
	
	

	protected CovidVaccinData() {
	}

	public CovidVaccinData(String totalInjectionCount, String newInjectionCount1, String newInjectionCount60,
			String injectionCompleteCount, String injectionCompletePercent) {
		super();
		this.totalInjectionCount = totalInjectionCount;
		this.newInjectionCount1 = newInjectionCount1;
		this.newInjectionCount60 = newInjectionCount60;
		this.injectionCompleteCount = injectionCompleteCount;
		this.injectionCompletePercent = injectionCompletePercent;
	}

	

} // CovidData Embeddable
