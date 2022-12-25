package com.hero.herolanding.repository;

import static com.hero.herolanding.domain.QCountry.*;
import static com.hero.herolanding.domain.QExchangeRate.*;
import static com.hero.herolanding.domain.QCovidData.*;
import static com.hero.herolanding.domain.QCountryPaper.*;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CountryPaper;
import com.hero.herolanding.domain.ExchangeRate;
import com.hero.herolanding.domain.Inspection;
import com.hero.herolanding.domain.QCountryPaper;
import com.hero.herolanding.domain.QInspection;
import com.hero.herolanding.domain.QVisa;
import com.hero.herolanding.domain.Visa;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeRepository {

	
	private final EntityManager em;
	private JPAQueryFactory queryFactory;
	
	// 메인페이지에서 사용
	
	// 전 세계 환율 정보 (크롤링) - 실시간 변동 (인서트 / 업데이트)
	public void insertExchange(ExchangeRate exchangeRate) {
		System.out.println("레파지토리");
		if(exchangeRate.getCurrencyNum() == null) { 
			em.persist(exchangeRate);
		}else {
			em.merge(exchangeRate);
		}
	}
	
	// 전체 나라 정보 가져오기
	public List<Country> findAllcountry() {
		queryFactory = new JPAQueryFactory(em);
		return queryFactory.selectFrom(country).fetch();
	}

	// 검색 후 사용
	// 검색한 나라의 정보 가져오기 ( 환율 , 코로나 수 , 필요 서류 , 검사 종류 , 여행자 순위 )
	// DTO를 여러 테이블 조인해서 작성해도 되는지? 안된다면 전부 셀렉트 만들기.
	public Country findCounrty(String countryone) {
		queryFactory = new JPAQueryFactory(em);
		Country findCountry = queryFactory.select(country).from(country).where(country.countryName.eq(countryone)).fetchOne();
		return findCountry;
	}
	
	// 환율정보(전체)
	public List<ExchangeRate> findExchageAll() { 
		queryFactory = new JPAQueryFactory(em);
		List<ExchangeRate> ExchangeRate =  queryFactory.selectFrom(exchangeRate).fetch();
		return ExchangeRate;
	
	}
	
	// 환율정보(단건)
	public ExchangeRate findExchageOne1(Long num) { 
		queryFactory = new JPAQueryFactory(em);
		ExchangeRate ExchangeRate =  queryFactory.selectFrom(exchangeRate).leftJoin(exchangeRate.countries , country).where(country.countryNum.eq(num)).fetchOne();
		return ExchangeRate;
	}
	
	// 코로나 수 (전체)
	public List<Country> findCovidAll() {
		queryFactory = new JPAQueryFactory(em);
		List<Country> covidDatas = queryFactory.selectFrom(country).where(country.covidData.milionCount.isNotNull()).fetch();
		return covidDatas;
	}
	
	// 코로나 수 (단건)
	public Tuple findCovidOne(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		Tuple covidData =  queryFactory.select(country.countryName, country.covidData).from(country).where(country.countryName.eq(countryName)).fetchOne();
		return covidData;
	}
	// 코로나 백신 수 (전체)
	public List<Country> findCovidVaccinAll() {
		queryFactory = new JPAQueryFactory(em);
		List<Country> covidDatas = queryFactory.selectFrom(country).where(country.covidData.milionCount.isNotNull()).fetch();
		return covidDatas;
	}
	
	// 나라 이름과 일치하는 정보들
	public Country findCountry(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		return queryFactory.selectFrom(country).where(country.countryName.eq(countryName)).fetchOne();
	}
	
	// 코로나 백신 수 (단건)
	public Tuple findCovidVaccinOne(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		Tuple covidData =  queryFactory.select(country.countryName, country.covidVaccinData).from(country).where(country.countryName.eq(countryName)).fetchOne();
		return covidData;
	}
	
	// 필요 서류 (단건)
	public List<CountryPaper> findCountryPeper(Long countryNum) {
		queryFactory = new JPAQueryFactory(em);
		List<CountryPaper> countryPaper = queryFactory.selectFrom(QCountryPaper.countryPaper)
												.leftJoin(QCountryPaper.countryPaper.country , country)
												.where(country.countryNum.eq(countryNum)).fetch();
		return countryPaper;
	}

	// 필요 검사 (단건)
	public List<Inspection> findinspection(Long countryNum) {
		queryFactory = new JPAQueryFactory(em);
		List<Inspection> inspection = queryFactory.selectFrom(QInspection.inspection)
											.leftJoin(QInspection.inspection.country , country)
											.where(country.countryNum.eq(countryNum)).fetch();
		return inspection;
	}
	
	// 비자 정보 (단건)
	public Visa findVisa(Long countryNum) {
		queryFactory = new JPAQueryFactory(em);
		Visa visa = queryFactory.selectFrom(QVisa.visa)
											.leftJoin(QVisa.visa.country , country)
											.where(country.countryNum.eq(countryNum)).fetchOne();
		return visa;
	}



	
	
	
	
}
