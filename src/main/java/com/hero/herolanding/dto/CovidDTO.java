package com.hero.herolanding.dto;

import javax.persistence.Column;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

@Getter @Setter
public class CovidDTO {
	
	private String country;
	
	private String totalCovidCount;

	private String newCovidCount1;
	
	private String newCovidCount60;

	private String milionCount;
	
	private String samang;

}
