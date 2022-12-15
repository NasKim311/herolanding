package com.hero.herolanding.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "papersIdSequence", sequenceName = "paper_seq", allocationSize = 1)
@Getter
@Setter
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "papersIdSequence")
	@Column(name = "서류번호")
	private int paperNum;

	@Column(name = "서류명")
	private String paperTitle;

	@Column(name = "발급기관명")
	private String paperAuthorityName;

	@Column(name = "발급기관링크")
	private String paperAuthorityLink;

	@Column(name = "비고")
	private String paperNote;

//--------<@OneToMany / CountryPaper>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "paper")
	private List<CountryPaper> countryPapers = new ArrayList<CountryPaper>();

} // Papers class
