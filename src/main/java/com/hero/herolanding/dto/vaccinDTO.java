package com.hero.herolanding.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class vaccinDTO {
	
	private String country;
	
	private String totalInjectionCount;

	private String newInjectionCount1;
	
	private String newInjectionCount60;

	private String injectionCompleteCount;
	
	private String injectionCompletePercent;

}
