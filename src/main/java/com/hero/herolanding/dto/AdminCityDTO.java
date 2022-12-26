package com.hero.herolanding.dto;

import com.hero.herolanding.domain.EntranceLevel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminCityDTO {
	
	private Long cityNum;
	private String cityName;
	private EntranceLevel entranceLevel;
	
}
