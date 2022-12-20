package com.hero.herolanding.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hero.herolanding.crawling.Covid;
import com.hero.herolanding.crawling.CovidVaaccin;
import com.hero.herolanding.crawling.ExchangeRateCr;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CovidData;
import com.hero.herolanding.domain.CovidVaccinData;
import com.hero.herolanding.domain.ExchangeRate;
import com.hero.herolanding.dto.CovidDTO;
import com.hero.herolanding.dto.vaccinDTO;
import com.hero.herolanding.repository.HomeRepository;

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

}
