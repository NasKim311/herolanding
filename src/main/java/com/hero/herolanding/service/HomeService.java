package com.hero.herolanding.service;

import static com.hero.herolanding.domain.QCountry.*;
import static com.hero.herolanding.domain.QExchangeRate.*;
import static com.hero.herolanding.domain.QCountry.*;
import static com.hero.herolanding.domain.QExchangeRate.*;
import static com.hero.herolanding.domain.QCovidData.*;
import static com.hero.herolanding.domain.QCountryPaper.*;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.crawling.Covid;
import com.hero.herolanding.crawling.CovidVaaccin;
import com.hero.herolanding.crawling.ExchangeRateCr;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CovidData;
import com.hero.herolanding.domain.CovidVaccinData;
import com.hero.herolanding.domain.ExchangeRate;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.CovidDTO;
import com.hero.herolanding.dto.CovidOneDTO;
import com.hero.herolanding.dto.vaccinDTO;
import com.hero.herolanding.repository.HomeRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	
	private final HomeRepository homeRepository;
	
//	@Transactional
//	public Country findCountryAll() {
//	
//		return homeRepository.findCountryAll();
//	}

	// 해당 국가 뉴스기사 ( 크롤링 , 디비 X )
	private final ExchangeRateCr exchangeRate;
	private final CovidVaaccin covidVaaccin;
	private final Covid covid;

	@Transactional
	public void save() {
		List<ExchangeRate> exchangeRates = exchangeRate.process();
		for(int i = 0 ; i < exchangeRates.size();i++) {
			homeRepository.insertExchange(exchangeRates.get(i));
		}
	}
	
	@Transactional
	public void saveCovidVaccin() {
		System.out.println("서비스 들어옴");
		List<vaccinDTO> covidVaccinDatas = covidVaaccin.process();
		for(int i = 0 ; i < covidVaccinDatas.size();i++) {
			Country country = homeRepository.findCounrty(covidVaccinDatas.get(i).getCountry());
			if(country != null) {
				CovidVaccinData covidVaccinData = new CovidVaccinData(
						covidVaccinDatas.get(i).getTotalInjectionCount(),
						covidVaccinDatas.get(i).getNewInjectionCount1(),
						covidVaccinDatas.get(i).getNewInjectionCount60(),
						covidVaccinDatas.get(i).getInjectionCompleteCount(),
						covidVaccinDatas.get(i).getInjectionCompletePercent());
				country.setCovidVaccinData(covidVaccinData);
			}
		}
	}
	@Transactional
	public void saveCovid() {
		System.out.println("서비스 들어옴");
		List<CovidDTO> covidDatas = covid.process();
		for(int i = 0 ; i < covidDatas.size();i++) {
			Country country = homeRepository.findCounrty(covidDatas.get(i).getCountry());
			if(country != null) {
				CovidData covidData = new CovidData(
						covidDatas.get(i).getTotalCovidCount(),
						covidDatas.get(i).getNewCovidCount1(),
						covidDatas.get(i).getNewCovidCount60(),
						covidDatas.get(i).getMilionCount(),
						covidDatas.get(i).getSamang());
				System.out.println("서비스");
				country.setCovidData(covidData);
			}
		}
	}
	
	
	@Transactional
	public List<CovidDTO> findCovid() {
		List<CovidDTO> covids = new ArrayList<CovidDTO>();
		List<Country> findcovid=  homeRepository.findCovidAll();
//		for(int i = 0 ; i < findcovid.size();i++) {
//			System.out.println("나라 : " + findcovid.get(i).getCountryName());
//			System.out.println("백만 : " + findcovid.get(i).getCovidData().getMilionCount());
//			System.out.println("신규 : " + findcovid.get(i).getCovidData().getNewCovidCount1());
//			System.out.println("사망 : " + findcovid.get(i).getCovidData().getSamang());
//		}
//		
		for(int i = 0 ; i < findcovid.size();i++) {
			CovidDTO covidDTO = new CovidDTO();
			covidDTO.setCountry(findcovid.get(i).getCountryName());
			covidDTO.setMilionCount(findcovid.get(i).getCovidData().getMilionCount());
			covidDTO.setNewCovidCount1(findcovid.get(i).getCovidData().getNewCovidCount1());
			covidDTO.setSamang(findcovid.get(i).getCovidData().getSamang());
			covidDTO.setTotalCovidCount(findcovid.get(i).getCovidData().getTotalCovidCount());
			
			covids.add(covidDTO);
		}

		return covids;
	}
	
	@Transactional
	public List<vaccinDTO> findCovidVaccin() {
		List<vaccinDTO> vaccins= new ArrayList<vaccinDTO>();
		List<Country> findcovid=  homeRepository.findCovidVaccinAll();
		
		for(int i = 0 ; i < findcovid.size();i++) {
			vaccinDTO vaccinDTO = new vaccinDTO();
			vaccinDTO.setCountry(findcovid.get(i).getCountryName());
			vaccinDTO.setInjectionCompleteCount(findcovid.get(i).getCovidVaccinData().getInjectionCompleteCount());
			vaccinDTO.setInjectionCompletePercent(findcovid.get(i).getCovidVaccinData().getInjectionCompletePercent());
			vaccinDTO.setNewInjectionCount1(findcovid.get(i).getCovidVaccinData().getNewInjectionCount1());
			vaccinDTO.setTotalInjectionCount(findcovid.get(i).getCovidData().getTotalCovidCount());
			
			vaccins.add(vaccinDTO);
		}
		
		return vaccins;
	}
	
	@Transactional
	public CovidOneDTO findCounrty(String country) {
		CovidOneDTO main = new CovidOneDTO();
		Country findone = homeRepository.findCounrty(country);
		
		main.setCountry(country);
		main.setInjectionCompleteCount(findone.getCovidVaccinData().getInjectionCompleteCount());
		main.setInjectionCompletePercent(findone.getCovidVaccinData().getInjectionCompletePercent());
		main.setNewCovidCount1(findone.getCovidData().getNewCovidCount1());
		main.setNewInjectionCount1(findone.getCovidVaccinData().getNewInjectionCount1());
		main.setSamang(findone.getCovidData().getSamang());
		main.setTotalInjectionCount(findone.getCovidVaccinData().getTotalInjectionCount());
		main.setTotalCovidCount(findone.getCovidData().getTotalCovidCount());
		
		return main;
		
	}

}
