package com.hero.herolanding.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class CovidVaccinData {

	@Column(name = "\"총 접종 횟수\"")
	private String totalInjectionCount;

	@Column(name = "\"신규 접종 횟수(1일)\"")
	private String newInjectionCount1;

	@Column(name = "\"신규 접종 횟수(60일)\"")
	private String newInjectionCount60;

	@Column(name = "\"백신 접종을 완료한 사람의 수\"")
	private String injectionCompleteCount;

	@Column(name = "\"인구 중 접종 완료자 비율\"")
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
