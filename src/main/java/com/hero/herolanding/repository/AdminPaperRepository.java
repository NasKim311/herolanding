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
import com.hero.herolanding.domain.Paper;
import com.hero.herolanding.dto.AdminCountryDTO;
import com.hero.herolanding.dto.AdminVisaDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminPaperRepository {

   private final EntityManager em;



// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------

   // 국가 테이블 가져오기 (국가 전체 찾기)
   public List<Paper> findAllPageList(){
	   return em.createQuery("select v from Paper v", Paper.class)
			   .getResultList();
   }
   // 국가 테이블 가져오기 (페이지에 해당하는 10개 국가)
   public List<Paper> findData(int nowPage){
	   return em.createQuery("select v from Paper v", Paper.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
   }
   
   // 국가 데이터 가져오기 (전체) search = 검색어 / select = 컬럼명 (countryName, continent...)
   public List<Paper> findSearchPageList(String select, String search) {
	   
	   List<Paper> findPaperName =  new ArrayList<Paper>();
	   
	   switch(select) {
	   case "paperNum":
		   Integer paperNumI = Integer.parseInt(search);
		   findPaperName = em.createQuery("select c from Paper c where paperNum = :paperNum", Paper.class)
			  				.setParameter("paperNum", paperNumI)
			  				.getResultList();
		   break;
		   
	   case "paperTitle":
		   findPaperName = em.createQuery("select c from Paper c where paperTitle = :paperTitle", Paper.class)
		   					.setParameter("paperTitle", search)
		   					.getResultList();
		   break;
		   
	   case "paperAuthorityName":
		   findPaperName = em.createQuery("select c from Paper c where paperAuthorityName = :paperAuthorityName", Paper.class)
		   					.setParameter("paperAuthorityName", search)
		   					.getResultList();
		   break;
		   
	   case "paperAuthorityLink":
		   findPaperName = em.createQuery("select c from Paper c where paperAuthorityLink = :paperAuthorityLink", Paper.class)
		   					.setParameter("paperAuthorityLink", search)
		   					.getResultList();
		   break;
		   
	   case "paperNote":
		   findPaperName = em.createQuery("select c from Paper c where paperNote = :paperNote", Paper.class)
		   					.setParameter("paperNote", search)
		   					.getResultList();
		   break;
		   
	   }
	   
	   return findPaperName;
	}
   
   // 검색한 국가 데이터 가져오기 (페이지에 해당하는 10개 국가)
   public List<Paper> findSearchData(String select, String search, int nowPage) {
	   
	   List<Paper> findPaperPageName =  new ArrayList<Paper>();
	   
	   switch(select) {
	   case "paperNum":
		   Integer paperNumI = Integer.parseInt(search);
		   findPaperPageName = em.createQuery("select c from Paper c where paperNum = :paperNum", Paper.class)
			  				.setParameter("paperNum", paperNumI)
			  				.setFirstResult(nowPage * 10 - 10)
			  				.setMaxResults(10)
			  				.getResultList();
		   break;
		   
	   case "paperTitle":
		   findPaperPageName = em.createQuery("select c from Paper c where paperTitle = :paperTitle", Paper.class)
		   					.setParameter("paperTitle", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "paperAuthorityName":
		   findPaperPageName = em.createQuery("select v from Paper v where paperAuthorityName = :paperAuthorityName", Paper.class)
		   					.setParameter("paperAuthorityName", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "paperAuthorityLink":
		   findPaperPageName = em.createQuery("select c from Paper c where paperAuthorityLink = :paperAuthorityLink", Paper.class)
		   					.setParameter("paperAuthorityLink", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   case "paperNote":
		   findPaperPageName = em.createQuery("select c from Paper c where paperNote", Paper.class)
		   					.setParameter("paperNote", search)
		   					.setFirstResult(nowPage * 10 - 10)
	 				  		.setMaxResults(10)
		   					.getResultList();
		   break;
		   
	   }
	   
	   return findPaperPageName;
	   
	   
   }
   
   // 국가 넘버로 국가 검색
   public List<Paper> findDataByNum(int paperNum) {
		return em.createQuery("select c from Paper c where paperNum = :paperNum", Paper.class)
				 .setParameter("paperNum", paperNum)
				 .getResultList();
	}
   
   // 국가 데이터 하나 찾아오기
   public Paper findOneData(int paperNum) {
	   return em.find(Paper.class, paperNum);
   }
   
   // 국가 데이터 삭제
   public void deleteData(int paperNum) {
	   Paper p = findOneData(paperNum);
	   em.remove(p);
	   em.flush();
	   em.clear();
	}
// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------
 
   


}