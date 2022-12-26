package com.hero.herolanding.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CountryPaper;
import com.hero.herolanding.domain.EntranceLevel;
import com.hero.herolanding.domain.Inspection;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Paper;
import com.hero.herolanding.domain.Report;
import com.hero.herolanding.domain.Visa;
import com.hero.herolanding.dto.AdminCountryDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminCountryRepository {

   private final EntityManager em;

   	// 회원 가입 날짜별로 가져오기
	public List<Long> findSignUpDate(){
		List<Long> member = em.createQuery("select count(m.signUpDate) from Member m group by signUpDate order by signUpDate desc")
							.getResultList();
		return member;
	}

// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------

   // 국가 테이블 가져오기 (국가 전체 찾기)
   public List<Country> findAllPageList(){
	   return em.createQuery("select c from Country c", Country.class)
			   .getResultList();
   }
   // 국가 테이블 가져오기 (페이지에 해당하는 10개 국가)
   public List<Country> findData(int nowPage){
	   return em.createQuery("select c from Country c", Country.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
   }
   
   // 국가 데이터 가져오기 (전체) search = 검색어 / select = 컬럼명 (countryName, continent...)
   public List<Country> findSearchPageList(String select, String search) {
	   List<Country> findCountryName =  new ArrayList<Country>();
	   
	   
	   switch(select) {
	   case "countryNum":
		   Long searchL = Long.parseLong(search);
		   findCountryName = em.createQuery("select c from Country c where countryNum = :countryNum", Country.class)
			  				   .setParameter("countryNum", searchL)
			  				   .getResultList();
		   break;
		   
	   case "countryName":
		   findCountryName = em.createQuery("select c from Country c where countryName = :countryName", Country.class)
		   						.setParameter("countryName", search)
		   						.getResultList();
		   break;
		   
	   case "continent":
		   Continent continentL = Continent.valueOf(search);
		   findCountryName = em.createQuery("select c from Country c where continent = :continent", Country.class)
		   						.setParameter("continent", continentL)
		   						.getResultList();
		   break;
		   
	   case "entranceLevel":
		   EntranceLevel entranceL = EntranceLevel.valueOf(search);
		   findCountryName = em.createQuery("select c from Country c where entranceLevel = :entranceLevel", Country.class)
		   						.setParameter("entranceLevel", entranceL)
		   						.getResultList();
		   break;
		   
	   case "embassyLink":
		   findCountryName = em.createQuery("select c from Country c where embassyLink = :embassyLink", Country.class)
		   						.setParameter("embassyLink", search)
		   						.getResultList();
		   break;
		   
	   case "countryNote":
		   findCountryName = em.createQuery("select c from Country c where countryNote = :countryNote", Country.class)
		   						.setParameter("countryNote", search)
		   						.getResultList();
		   break;
	   }
	   
	   return findCountryName;
	}
   
   // 검색한 국가 데이터 가져오기 (페이지에 해당하는 10개 국가)
   public List<Country> findSearchData(String select, String search, int nowPage) {
	   
	   List<Country> findCountryByName = new ArrayList<Country>();
	   
	   switch(select) {
	   
	   case "countryNum":
		   Long searchL = Long.parseLong(search);
		   findCountryByName = em.createQuery("select c from Country c where countryNum = :countryNum", Country.class)
	 				  		   .setParameter("countryNum", searchL)
	 				  		   .setFirstResult(nowPage * 10 - 10)
	 				  		   .setMaxResults(10)
	 				  		   .getResultList();
		   break;
		   
	   case "countryName":
		   findCountryByName =  em.createQuery("select c from Country c where countryName = :countryName", Country.class)
				 				  .setParameter("countryName", search)
				 				  .setFirstResult(nowPage * 10 - 10)
				 				  .setMaxResults(10)
				 				  .getResultList();
		   break;
		   
	   case "continent":
		   Continent continentL = Continent.valueOf(search);
		   findCountryByName = em.createQuery("select c from Country c where continent = :continent", Country.class)
	 				  			 .setParameter("continent", continentL)
	 				  			 .setFirstResult(nowPage * 10 - 10)
	 				  			 .setMaxResults(10)
	 				  			 .getResultList();
		   break;
		   
	   case "entranceLevel":
		   EntranceLevel entranceL = EntranceLevel.valueOf(search);
		   findCountryByName = em.createQuery("select c from Country c where entranceLevel = :entranceLevel", Country.class)
	 				  			 .setParameter("entranceLevel", entranceL)
	 				  			 .setFirstResult(nowPage * 10 - 10)
	 				  			 .setMaxResults(10)
	 				  			 .getResultList();
		   break;
		   
	   case "embassyLink":
		   findCountryByName = em.createQuery("select c from Country c where embassyLink = :embassyLink", Country.class)
	 				  			 .setParameter("embassyLink", search)
	 				  			 .setFirstResult(nowPage * 10 - 10)
	 				  			 .setMaxResults(10)
	 				  			 .getResultList();
		   break;
		   
	   case "countryNote":
		   findCountryByName = em.createQuery("select c from Country c where countryNote = :countryNote", Country.class)
	 				  			 .setParameter("countryNote", search)
	 				  			 .setFirstResult(nowPage * 10 - 10)
	 				  			 .setMaxResults(10)
	 				  			 .getResultList();
		   break;
	   }
	   
	   return findCountryByName;
   }
   
   // 국가 넘버로 국가 검색
   public List<Country> findDataByNum(Long countryNumL) {
		return em.createQuery("select c from Country c where countryNum = :countryNum", Country.class)
				 .setParameter("countryNum", countryNumL)
				 .getResultList();
	}
   
   // 국가 데이터 하나 찾아오기
   public Country findOneData(Long countryNum) {
	   return em.find(Country.class, countryNum);
   }
   
   // 국가 데이터 삭제
   public void deleteCountry(int countryNum) {
	   Long countryNum_ = Long.valueOf(countryNum); 
	   Country c = findOneData(countryNum_);
	   em.remove(c);
	   em.flush();
	   em.clear();
	}
// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------
 
   


}