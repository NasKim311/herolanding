package com.hero.herolanding.domain;

public enum BoardType {
	   TIP("꿀팁게시판"), FREE("자유게시판");
	
	   private final String description;
	   private BoardType(String  description)
	   {
	      this.description = description;
	   }
	   public String getDescription()
	   {
	      return description;
	   }
	}
 // BoardType enum
