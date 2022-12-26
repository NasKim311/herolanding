package com.hero.herolanding.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.EntranceLevel;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminCityRepository {
	
	private final EntityManager em;
	
	// 도시 테이블 가져오기 (도시 전체 찾기)
	public List<City> findAllPageList() {
		return em.createQuery("select c from City c", City.class)
				   .getResultList();
	}
	
	// 도시 테이블 10개씩 잘라서 가져오기
	public List<City> findData(int nowPage) {
		return em.createQuery("select c from City c", City.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
	}

	// 도시 데이터 가져오기(전체) search = 검색어 / select = 컬럼명 (countryName, continent 등등...)
	public List<City> findSearchPageList(String select, String search) {
		
		List<City> findCityName = new ArrayList<City>();
		
		switch(select) {
		case "cityNum": // 도시번호를 검색한 경우
			Long searchL = Long.parseLong(search); 
			findCityName = em.createQuery("select c from City c where cityNum = :cityNum", City.class)
	  				   		 .setParameter("cityNum", searchL)
	  				   		 .getResultList();
			break;
			
		case "cityName": // 도시 이름을 검색한 경우
			findCityName = em.createQuery("select c from City c where cityName = :cityName", City.class)
							 .setParameter("cityName", search)
							 .getResultList();
			break;
			
		case "entranceLevel": // 입국조치 번호를 검색한 경우
			EntranceLevel entranceLevel = EntranceLevel.valueOf(search);
			findCityName = em.createQuery("select c from City c where entranceLevel = :entranceLevel", City.class)
							   .setParameter("entranceLevel", entranceLevel)
							   .getResultList();
			break;
		}
		
		return findCityName;
	}

	// 도시 데이터 10개씩 나눠서 가져오기
	public List<City> findSearchData(String select, String search, int nowPage) {
		
		List<City> findCityByName = new ArrayList<City>();
		
		switch(select) {
		case "cityNum": // 도시번호를 검색한 경우
			Long searchL = Long.parseLong(search); 
			findCityByName = em.createQuery("select c from City c where cityNum = :cityNum", City.class)
							   .setParameter("cityNum", searchL)
							   .setFirstResult(nowPage * 10 - 10)
							   .setMaxResults(10)
							   .getResultList();
			break;
			
		case "cityName": // 도시 이름을 검색한 경우
			findCityByName =  em.createQuery("select c from City c where cityName = :cityName", City.class)
	 				  			.setParameter("cityName", search)
	 				  			.setFirstResult(nowPage * 10 - 10)
	 				  			.setMaxResults(10)
	 				  			.getResultList();
			break;
			
		case "entranceLevel": // 입국조치 번호를 검색한 경우
			EntranceLevel entranceLevel = EntranceLevel.valueOf(search);
			findCityByName =  em.createQuery("select c from City c where entranceLevel = :entranceLevel", City.class)
								.setParameter("entranceLevel", entranceLevel)
								.setFirstResult(nowPage * 10 - 10)
								.setMaxResults(10)
								.getResultList();
			break;
		}
		
		return findCityByName;
	}

	// 도시 넘버로 도시 검색
	public List<City> findDataByNum(Long cityNumL) {
		return em.createQuery("select c from City c where cityNum = :cityNum", City.class)
				 .setParameter("cityNum", cityNumL)
				 .getResultList();
	}

	// 도시 데이터 하나 찾아오기
	public City findOneData(Long cityNum) {
		return em.find(City.class, cityNum);
	}

	public void deleteCity(int cityNum) {
		Long cityNum_ = Long.valueOf(cityNum);
		City c = findOneData(cityNum_);
		em.remove(c);
		em.flush();
		em.clear();
	}

}
















