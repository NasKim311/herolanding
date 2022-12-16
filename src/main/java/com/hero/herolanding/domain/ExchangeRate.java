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
@SequenceGenerator(name = "ExchangeRateIdSequence", sequenceName = "ExchangeRate_seq", allocationSize = 1)
@Getter
@Setter
public class ExchangeRate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExchangeRateIdSequence")
	@Column(name = "통화번호")
	private Long currencyNum;

	@Column(name = "통화명")
	private String currencyName;

	@Column(name = "매매기준율")
	private String tradingStandardRate;

	@Column(name = "전일대비") // 전일대비 환율 상승/하락 폭 데이터
	private String joenIlDaeBi;

	@Column(name = "등락율")
	private String joenIlDaeBiRate;

	@Column(name = "현찰구입")
	private String buyingCash;

	@Column(name = "현찰판매")
	private String sellingCash;

	@Column(name = "송금받을때")
	private String takeMoney;

	@Column(name = "송금보낼때")
	private String giveMoney;

//--------<@OneToMany / Country>-------------------------------------------------------------------------------------	
	@OneToMany(mappedBy = "exchangeRate")
	private List<Country> countries = new ArrayList<Country>();



} // ExchangeRate class
