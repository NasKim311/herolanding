package com.hero.herolanding.repository;



import static com.hero.herolanding.domain.QCountry.*;
import static com.hero.herolanding.domain.QExchangeRate.*;
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
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeRepository {

	
	private final EntityManager em;
	private JPAQueryFactory queryFactory;
//	private final queryFactory = new JPAQueryFactory(em);
	
	
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
	// 나라 업데이트 
	public void updateCountry() {
		
	}
	
	// 등록된 회원의 많이 여행가는 나라 순위 (디비 - select)
	public void maxCntContry() {
		
	}
	// 코로나 확진자 수 순위 (크롤링) - 매일 변동 (인서트)
	public void covidInsert() {
		
	}
	// 코로나 확진자 수 순위 (크롤링) - 매일 변동 (업데이트)
	public void covidUpdate() {
		
	}

	// 검색 후 사용
	// 검색한 나라의 정보 가져오기 ( 환율 , 코로나 수 , 필요 서류 , 검사 종류 , 여행자 순위 )
	// DTO를 여러 테이블 조인해서 작성해도 되는지? 안된다면 전부 셀렉트 만들기.
	public Country findCounrty(String countryone) {
		queryFactory = new JPAQueryFactory(em);
		Country findCountry = queryFactory.select(country).from(country).where(country.countryName.eq(countryone)).fetchOne();
//		if(findCountry == null) {
//			return em.persist(findCountry);
//		}
		return findCountry;
	}
	
	// 환율정보(전체)
	public List<ExchangeRate> findExchageAll() { 
		queryFactory = new JPAQueryFactory(em);
		List<ExchangeRate> ExchangeRate =  queryFactory.selectFrom(exchangeRate).fetch();
		return ExchangeRate;
	
	}
	
	// 환율정보(단건)
	public ExchangeRate findExchageOne(String country) { 
		queryFactory = new JPAQueryFactory(em);
		ExchangeRate ExchangeRate =  queryFactory.selectFrom(exchangeRate).where(exchangeRate.countries.get(0).countryName.eq(country)).fetchOne();
		return ExchangeRate;
	}
	
	// 코로나 수 (전체)
	public List<Tuple> findCovidAll() {
		queryFactory = new JPAQueryFactory(em);
		List<Tuple> covidDatas =  queryFactory.select(country.countryName,country.covidData).from(country).fetch();
		return covidDatas;
	}
	
	// 코로나 수 (단건)
	public Tuple findCovidOne(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		Tuple covidData =  queryFactory.select(country.countryName, country.covidData).from(country).where(country.countryName.eq(countryName)).fetchOne();
		return covidData;
	}
	// 코로나 백신 수 (전체)
	public List<Tuple> findCovidVaccinAll() {
		queryFactory = new JPAQueryFactory(em);
		List<Tuple> covidDatas =  queryFactory.select(country.countryName,country.covidVaccinData).from(country).fetch();
		return covidDatas;
	}
	
	// 코로나 백신 수 (단건)
	public Tuple findCovidVaccinOne(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		Tuple covidData =  queryFactory.select(country.countryName, country.covidVaccinData).from(country).where(country.countryName.eq(countryName)).fetchOne();
		return covidData;
	}
	
	// 필요 서류 (단건)
	public CountryPaper findCountryPeper(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		CountryPaper countryPaper = queryFactory.selectFrom(QCountryPaper.countryPaper)
												.where(QCountryPaper.countryPaper.country.countryName
												.eq(countryName)).fetchOne();
		
		return countryPaper;
		
	}
	// 필요 검사 (단건)
	public Inspection findinspection(String countryName) {
		queryFactory = new JPAQueryFactory(em);
		Inspection inspection = queryFactory.selectFrom(QInspection.inspection)
											.where(QCountryPaper.countryPaper.country.countryName
											.eq(countryName)).fetchOne();
		
		return inspection;
				
	}

	// 여행자 순위 (전체)
	public void findTripLank() {
		
	}
	
	// 세계지도에 들어갈 정보
	public void getworld() {
		
		
	}

	
	
	
	
}
