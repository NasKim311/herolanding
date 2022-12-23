package com.hero.herolanding.domain;

public enum BoardType {
	REVIEW("후기게시판"), FREE("자유게시판"), FOOD("맛집게시판");

	private final String description;

	private BoardType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
// BoardType enum
