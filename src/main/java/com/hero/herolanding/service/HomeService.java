package com.hero.herolanding.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hero.herolanding.crawling.ExchangeRateCr;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.ExchangeRate;
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

	@Transactional
	public void save() {
		List<ExchangeRate> exchangeRates = exchangeRate.process();
		for(int i = 0 ; i < exchangeRates.size();i++) {
			homeRepository.insertExchange(exchangeRates.get(i));
		}
	}

}
