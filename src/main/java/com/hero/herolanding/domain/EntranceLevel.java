package com.hero.herolanding.domain;

public enum EntranceLevel {
	EL1 ("입국금지"), EL2("백신 접종 조건부 허용") ,EL3("시설격리조치"), EL4("검역강화 및 권고사항"), EL5("한국발 입국자에 대한 입국관련 조치 해제") ;

	private final String description;

	private EntranceLevel(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
} // EntranceLevel enum
