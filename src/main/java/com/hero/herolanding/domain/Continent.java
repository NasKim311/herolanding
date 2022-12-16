package com.hero.herolanding.domain;

public enum Continent {
	   ASIA("아시아") , EUROPE("유럽") , NorthAmerica("북아메리카") , SouthAmerica("남아메리카"), AFRICA("아프리카") , OCEANIA("오세아니아");
	   
	   private final String description;
	   
	   private Continent(String  description)
	   {
	      this.description = description;
	   }
	   
	   public String getDescription()
	   {
	      return description;
	   }
} // Continent enum
