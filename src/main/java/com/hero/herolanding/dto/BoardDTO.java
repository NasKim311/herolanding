package com.hero.herolanding.dto;


import com.hero.herolanding.domain.BoardType;
import com.hero.herolanding.domain.Continent;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {
	
	 private String post_title;
	 private String post_content;
	 private Continent continent;
	 private BoardType writeType;
	 private String modifiedDate;
}
