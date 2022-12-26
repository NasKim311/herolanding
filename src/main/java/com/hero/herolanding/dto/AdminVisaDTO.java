package com.hero.herolanding.dto;

import lombok.Setter;

import lombok.Getter;

@Getter @Setter
public class AdminVisaDTO {
	
	private int visaNum;
	private String entranceVisaStatus;
	private String normalPassportStatus;
	private String normalPassportPeriod;
	private String officialPassportStatus;
	private String officialPassportPeriod;
	private String diplomatPassportStatus;
	private String diplomatPassportPeriod;
	private String reasonForVisaFree;
	private String visaNote;
}
