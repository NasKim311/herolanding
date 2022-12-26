package com.hero.herolanding.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CountryPaper;
import com.hero.herolanding.domain.EntranceLevel;
import com.hero.herolanding.domain.Inspection;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminCountryPaperRepository {
	
	private final EntityManager em;
	
	// 도시 테이블 가져오기 (도시 전체 찾기)
	public List<CountryPaper> findAllPageList() {
		return em.createQuery("select cp from CountryPaper cp", CountryPaper.class)
				   .getResultList();
	}
	
	// 도시 테이블 10개씩 잘라서 가져오기
	public List<CountryPaper> findData(int nowPage) {
		return em.createQuery("select cp from CountryPaper cp", CountryPaper.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
	}

	// 도시 데이터 가져오기(전체) search = 검색어 / select = 컬럼명 (countryName, continent 등등...)
	public List<CountryPaper> findSearchPageList(String select, String search) {
		List<CountryPaper> findCountryPaperName = new ArrayList<CountryPaper>();
		
		switch(select) {
		case "countryPaperNum": // 국가별서류번호를 검색한 경우
			Integer searchInt = Integer.parseInt(search);
			findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperNum = :countryPaperNum", CountryPaper.class)
	  				   		 		.setParameter("countryPaperNum", searchInt)
	  				   		 		.getResultList();
			break;
			
		case "countryPaperMinAge": // 최소나이를 검색한 경우
			findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperMinAge = :countryPaperMinAge", CountryPaper.class)
									.setParameter("countryPaperMinAge", search)
									.getResultList();
			break;
			
		case "countryPaperExpiration": // 서류유효기간을 검색한 경우
			findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperExpiration = :countryPaperExpiration", CountryPaper.class)
							   		.setParameter("countryPaperExpiration", search)
							   		.getResultList();
			break;
			
		case "countryPaperSubmitType": // 서류제출방법을 검색한 경우
			findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperSubmitType = :countryPaperSubmitType", CountryPaper.class)
									.setParameter("countryPaperSubmitType", search)
									.getResultList();
			break;
			
		case "countryPaperNote": // 비고를 검색한 경우
			findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperNote = :countryPaperNote", CountryPaper.class)
									.setParameter("countryPaperNote", search)
									.getResultList();
			break;
		}
		
		return findCountryPaperName;
		
	}

	// 검사 데이터 10개씩 나눠서 가져오기
public List<CountryPaper> findSearchData(String select, String search, int nowPage) {
	List<CountryPaper> findCountryPaperName = new ArrayList<CountryPaper>();
	
	switch(select) {
	case "countryPaperNum": // 국가별서류번호를 검색한 경우
		Integer searchInt = Integer.parseInt(search);
		findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperNum = :countryPaperNum", CountryPaper.class)
  				   		 		.setParameter("countryPaperNum", searchInt)
  				   		 		.setFirstResult(nowPage * 10 - 10)
  				   		 		.setMaxResults(10)
  				   		 		.getResultList();
		break;
		
	case "countryPaperMinAge": // 최소나이를 검색한 경우
		findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperMinAge = :countryPaperMinAge", CountryPaper.class)
								.setParameter("countryPaperMinAge", search)
								.setFirstResult(nowPage * 10 - 10)
  				   		 		.setMaxResults(10)
								.getResultList();
		break;
		
	case "countryPaperExpiration": // 서류유효기간을 검색한 경우
		findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperExpiration = :countryPaperExpiration", CountryPaper.class)
						   		.setParameter("countryPaperExpiration", search)
						   		.setFirstResult(nowPage * 10 - 10)
  				   		 		.setMaxResults(10)
						   		.getResultList();
		break;
		
	case "countryPaperSubmitType": // 서류제출방법을 검색한 경우
		findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperSubmitType = :countryPaperSubmitType", CountryPaper.class)
								.setParameter("countryPaperSubmitType", search)
								.setFirstResult(nowPage * 10 - 10)
  				   		 		.setMaxResults(10)
								.getResultList();
		break;
		
	case "countryPaperNote": // 비고를 검색한 경우
		findCountryPaperName = em.createQuery("select cp from CountryPaper cp where countryPaperNote = :countryPaperNote", CountryPaper.class)
								.setParameter("countryPaperNote", search)
								.setFirstResult(nowPage * 10 - 10)
  				   		 		.setMaxResults(10)
								.getResultList();
		break;
	}
		
		return findCountryPaperName;
	}

	// 도시 넘버로 도시 검색
	public List<CountryPaper> findDataByNum(int countryPaperNum) {
		return em.createQuery("select cp from CountryPaper cp where countryPaperNum = :countryPaperNum", CountryPaper.class)
				 .setParameter("countryPaperNum", countryPaperNum)
				 .getResultList();
	}

	// 도시 데이터 하나 찾아오기
	public CountryPaper findOneData(int countryPaperNum) {
		return em.find(CountryPaper.class, countryPaperNum);
	}

	public void deleteInspection(int countryPaperNum) {
		CountryPaper cp = findOneData(countryPaperNum);
		em.remove(cp);
		em.flush();
		em.clear();
	}

}
















