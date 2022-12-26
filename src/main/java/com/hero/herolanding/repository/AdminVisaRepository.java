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
import com.hero.herolanding.dto.AdminVisaDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminVisaRepository {

   private final EntityManager em;



// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------

   // 국가 테이블 가져오기 (국가 전체 찾기)
   public List<Visa> findAllPageList(){
	   return em.createQuery("select v from Visa v", Visa.class)
			   .getResultList();
   }
   // 국가 테이블 가져오기 (페이지에 해당하는 10개 국가)
   public List<Visa> findData(int nowPage){
	   return em.createQuery("select v from Visa v", Visa.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
   }
   
   // 국가 데이터 가져오기 (전체) search = 검색어 / select = 컬럼명 (countryName, continent...)
   public List<Visa> findSearchPageList(String select, String search) {
	   List<Visa> findVisaName =  new ArrayList<Visa>();
	   
	   
	   switch(select) {
	   case "visaNum":
		   Integer visaNumI = Integer.parseInt(search);
		   findVisaName = em.createQuery("select v from Visa v where visaNum = :visaNum", Visa.class)
			  				.setParameter("visaNum", visaNumI)
			  				.getResultList();
		   break;
		   
	   case "entranceVisaStatus":
		   
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   
		   findVisaName = em.createQuery("select v from Visa v where entranceVisaStatus = :entranceVisaStatus", Visa.class)
		   					.setParameter("entranceVisaStatus", search)
		   					.getResultList();
		   break;
		   
	   case "normalPassportStatus":
		   
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   
		   findVisaName = em.createQuery("select v from Visa v where normalPassportStatus = :normalPassportStatus", Visa.class)
		   					.setParameter("normalPassportStatus", search)
		   					.getResultList();
		   break;
		   
	   case "normalPassportPeriod":
		   findVisaName = em.createQuery("select c from Visa c where normalPassportPeriod = :normalPassportPeriod", Visa.class)
		   					.setParameter("normalPassportPeriod", search)
		   					.getResultList();
		   break;
		   
	   case "officialPassportStatus":
		   
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   
		   findVisaName = em.createQuery("select c from Visa c where officialPassportStatus = :officialPassportStatus", Visa.class)
		   					.setParameter("officialPassportStatus", search)
		   					.getResultList();
		   break;
		   
	   case "officialPassportPeriod":
		   findVisaName = em.createQuery("select c from Visa c where officialPassportPeriod = :officialPassportPeriod", Visa.class)
		   					.setParameter("officialPassportPeriod", search)
		   					.getResultList();
		   break;
	   case "diplomatPassportStatus":
		   
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   
		   findVisaName = em.createQuery("select c from Visa c where diplomatPassportStatus = :diplomatPassportStatus", Visa.class)
		   					.setParameter("diplomatPassportStatus", search)
		   					.getResultList();
		   break;
	   case "diplomatPassportPeriod":
		   findVisaName = em.createQuery("select c from Visa c where diplomatPassportPeriod = :diplomatPassportPeriod", Visa.class)
		   					.setParameter("diplomatPassportPeriod", search)
		   					.getResultList();
		   break;
	   case "reasonForVisaFree":
		   findVisaName = em.createQuery("select c from Visa c where reasonForVisaFree = :reasonForVisaFree", Visa.class)
		   					.setParameter("reasonForVisaFree", search)
		   					.getResultList();
		   break;
	   case "visaNote":
		   findVisaName = em.createQuery("select c from Visa c where visaNote = :visaNote", Visa.class)
		   					.setParameter("visaNote", search)
		   					.getResultList();
		   break;
	   }
	   
	   return findVisaName;
	}
   
   // 검색한 국가 데이터 가져오기 (페이지에 해당하는 10개 국가)
   public List<Visa> findSearchData(String select, String search, int nowPage) {
	   List<Visa> findVisaFindName =  new ArrayList<Visa>();
	   
	   
	   switch(select) {
	   case "visaNum":
		   Integer visaNumI = Integer.parseInt(search);
		   findVisaFindName = em.createQuery("select v from Visa v where visaNum = :visaNum", Visa.class)
			  				.setParameter("visaNum", visaNumI)
			  				.setFirstResult(nowPage * 10 - 10)
			  				.setMaxResults(10)
			  				.getResultList();
		   break;
		   
	   case "entranceVisaStatus":
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   findVisaFindName = em.createQuery("select v from Visa v where entranceVisaStatus = :entranceVisaStatus", Visa.class)
		   					.setParameter("entranceVisaStatus", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "normalPassportStatus":
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   findVisaFindName = em.createQuery("select v from Visa v where normalPassportStatus = :normalPassportStatus", Visa.class)
		   					.setParameter("normalPassportStatus", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "normalPassportPeriod":
		   findVisaFindName = em.createQuery("select c from Visa c where normalPassportPeriod = :normalPassportPeriod", Visa.class)
		   					.setParameter("normalPassportPeriod", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "officialPassportStatus":
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   findVisaFindName = em.createQuery("select c from Visa c where officialPassportStatus = :officialPassportStatus", Visa.class)
		   					.setParameter("officialPassportStatus", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "officialPassportPeriod":
		   findVisaFindName = em.createQuery("select c from Visa c where officialPassportPeriod = :officialPassportPeriod", Visa.class)
		   					.setParameter("officialPassportPeriod", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
	   case "diplomatPassportStatus":
		   if(search == "가능") {
			   search = "true";
		   }else if(search == "불가능") {
			   search = "false";
		   }
		   findVisaFindName = em.createQuery("select c from Visa c where diplomatPassportStatus = :diplomatPassportStatus", Visa.class)
		   					.setParameter("diplomatPassportStatus", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
	   case "diplomatPassportPeriod":
		   findVisaFindName = em.createQuery("select c from Visa c where diplomatPassportPeriod = :diplomatPassportPeriod", Visa.class)
		   					.setParameter("diplomatPassportPeriod", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
	   case "reasonForVisaFree":
		   findVisaFindName = em.createQuery("select c from Visa c where reasonForVisaFree = :reasonForVisaFree", Visa.class)
		   					.setParameter("reasonForVisaFree", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
	   case "visaNote":
		   findVisaFindName = em.createQuery("select c from Visa c where visaNote = :visaNote", Visa.class)
		   					.setParameter("visaNote", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
	   }
	   
	   return findVisaFindName;
	   
	   
   }
   
   // 국가 넘버로 국가 검색
   public List<Visa> findDataByNum(int visaNum) {
		return em.createQuery("select c from Visa c where visaNum = :visaNum", Visa.class)
				 .setParameter("visaNum", visaNum)
				 .getResultList();
	}
   
   // 국가 데이터 하나 찾아오기
   public Visa findOneData(int visaNum) {
	   return em.find(Visa.class, visaNum);
   }
   
   // 국가 데이터 삭제
   public void deleteData(int visaNum) {
	   Visa v = findOneData(visaNum);
	   em.remove(v);
	   em.flush();
	   em.clear();
	}
// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------
 
   


}