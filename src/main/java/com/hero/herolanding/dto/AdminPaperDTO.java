package com.hero.herolanding.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminPaperDTO {

	private int paperNum;
	private String paperTitle;
	private String paperAuthorityName;
	private String paperAuthorityLink;
	private String paperNote;
}
