package com.hero.herolanding.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class countryPaperDTO {
	
	private String paperAuthorityName;
	
	private String paperName;
	
	private String countryPaperMinAge;

	private String countryPaperExpiration;

	private String countryPaperSubmitType;

	private String countryPaperNote;

}
