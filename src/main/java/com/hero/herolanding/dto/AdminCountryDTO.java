package com.hero.herolanding.dto;


import javax.persistence.Column;

import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.EntranceLevel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminCountryDTO {

	private Long countryNum;
	private String countryName;
	private Continent continent;
	private EntranceLevel entranceLevel;
	private String embassyLink;
	private String countryNote;
	
}
