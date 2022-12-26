package com.hero.herolanding.dto;


import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.hero.herolanding.domain.BoardType;
import com.hero.herolanding.domain.Continent;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminBoardDTO {
	
	private Long boardNum;
	private String insertDate;
	private String updateDate;
	private String boardTitle;
	private String boardContents;
	private Long boardCount;
	private Long reportCount;
	private Continent continent;
	private BoardType boardType;
}
