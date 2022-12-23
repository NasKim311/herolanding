package com.hero.herolanding.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportDTO {
	
	String reportContents;
	long boardId;
	long memberId;
	long commentId;
}
