package com.hero.herolanding.service;

import static com.hero.herolanding.domain.QCountry.*;
import static com.hero.herolanding.domain.QExchangeRate.*;
import static com.hero.herolanding.domain.QCountry.*;
import static com.hero.herolanding.domain.QExchangeRate.*;
import static com.hero.herolanding.domain.QCovidData.*;
import static com.hero.herolanding.domain.QCountryPaper.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.crawling.Covid;
import com.hero.herolanding.crawling.CovidVaaccin;
import com.hero.herolanding.crawling.ExchangeRateCr;
import com.hero.herolanding.crawling.News;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CountryPaper;
import com.hero.herolanding.domain.CovidData;
import com.hero.herolanding.domain.CovidVaccinData;
import com.hero.herolanding.domain.ExchangeRate;
import com.hero.herolanding.domain.Inspection;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Visa;
import com.hero.herolanding.dto.CovidDTO;
import com.hero.herolanding.dto.CovidOneDTO;
import com.hero.herolanding.dto.NewsDTO;
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

	private final ExchangeRateCr exchangeRate;
	private final CovidVaaccin covidVaaccin;
	private final Covid covid;
	private final News news;
	
	
	
	// 전체 환율 정보 저장
	@Transactional
	public void save() {
		List<ExchangeRate> exchangeRates = exchangeRate.process();
		for(int i = 0 ; i < exchangeRates.size();i++) {
			homeRepository.insertExchange(exchangeRates.get(i));
		}
	}
	
	

	
	// 코로나 백신 전체 정보 저장하기
	@Transactional
	public void saveCovidVaccin() {
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
	
	// 코로나 확진자 전체 정보 저장하기
	@Transactional
	public void saveCovid() {
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
	
	// 코로나 확진자 전체 정보 가져오기
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
	
	
	// 코로나 백신 전체 정보 가져오기
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
	
	// 코로나 정보 한 나라의 값 가져오기
	@Transactional
	public CovidOneDTO findCoivdCounrty(String country) {
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
	
	// 검색 후 결과 값 가지고오기==============================================================================================
	// 검색한 나라정보
	@Transactional
	public Country findCountry(String countryName) {
		return homeRepository.findCounrty(countryName);
	}

	// 검색한 나라의 필요서류 정보
	@Transactional
	public List<CountryPaper> findCountryPaper(String countryName) {
		Long countryNum =  homeRepository.findCounrty(countryName).getCountryNum();
		return homeRepository.findCountryPeper(countryNum);
		
	}
	// 검색한 나라의 접종 정보
	@Transactional
	public List<Inspection> findInspection(String countryName) {
		Long countryNum =  homeRepository.findCounrty(countryName).getCountryNum();
		return homeRepository.findinspection(countryNum);
		
	}
	// 해당 나라의 환율 가져오기
	@Transactional
	public ExchangeRate exchangeOne(String countryName) {
		Long countryNum =  homeRepository.findCounrty(countryName).getCountryNum();
		List<ExchangeRate> exchangeRates = homeRepository.findExchageAll();
		return homeRepository.findExchageOne1(countryNum);
	}
	// 검색한 나라의 비자 정보
	@Transactional
	public Visa findVisa(String countryName){
		Long countryNum =  homeRepository.findCounrty(countryName).getCountryNum();
		return homeRepository.findVisa(countryNum);
	}
	// 검색한 나라의 뉴스 크롤링
	@Transactional
	public List<NewsDTO> findNews(String countryName) {
		return news.process(countryName);
	}
	// 검색 후 결과 값 가지고오기==============================================================================================
	
	
	// 코로나 정보 지도에 업데이트 하기
	@SuppressWarnings("unchecked")
	@Transactional
	public void coivdMap() throws FileNotFoundException, IOException, ParseException {
		List<Country> countrys =  homeRepository.findAllcountry();
		JSONArray jsarr = (JSONArray) new JSONParser().parse(new FileReader("src/main/resources/static/js/country.json"));
		JSONArray hey = new JSONArray();
		for(int i = 0 ; i < countrys.size();i++) {
			for(int j = 0 ; j < jsarr.size(); j++) {
				JSONObject jsonObject = (JSONObject) jsarr.get(j);
				if(countrys.get(i).getCountryName().equals(jsonObject.get("name"))) {
					System.out.println(jsonObject.get("name"));
					if(countrys.get(i).getCovidData() == null) {
						continue;
					}else {
						System.out.println(countrys.get(i).getCovidData().getMilionCount());
						int cnt = Integer.parseInt(countrys.get(i).getCovidData().getMilionCount().replace(",",""));
						jsonObject.replace("value", cnt);
						hey.add(jsonObject);
						
					}
				}
			}
		}
		
		FileWriter hi = new  FileWriter("src/main/resources/static/js/country.json");
		hi.write(hey.toJSONString());
		hi.flush();
		hi.close();
		JSONObject jsonObject = (JSONObject) jsarr.get(0);
		System.out.println(jsonObject);
		System.out.println(jsonObject.get("value"));
		System.out.println();
	}
}
