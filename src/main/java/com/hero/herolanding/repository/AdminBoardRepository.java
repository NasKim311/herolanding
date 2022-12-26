package com.hero.herolanding.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.BoardType;
import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Board;
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
public class AdminBoardRepository {

   private final EntityManager em;

   	// 회원 가입 날짜별로 가져오기
	public List<Long> findSignUpDate(){
		List<Long> member = em.createQuery("select count(m.signUpDate) from Member m group by signUpDate order by signUpDate desc")
							.getResultList();
		return member;
	}

// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------

   // 국가 테이블 가져오기 (국가 전체 찾기)
   public List<Board> findAllPageList(){
	   return em.createQuery("select c from Board c", Board.class)
			   .getResultList();
   }
   // 국가 테이블 가져오기 (페이지에 해당하는 10개 국가)
   public List<Board> findData(int nowPage){
	   return em.createQuery("select c from Board c", Board.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
   }
   
   // 국가 데이터 가져오기 (전체) search = 검색어 / select = 컬럼명 (countryName, continent...)
   public List<Board> findSearchPageList(String select, String search) {
	   List<Board> findCountryName =  new ArrayList<Board>();
	   System.out.println("======================================"+select);
	   System.out.println("======================================"+search);
	   
	   switch(select) {
	   case "boardNum":
		   Long searchL = Long.parseLong(search);
		   findCountryName = em.createQuery("select c from Board c where boardNum = :boardNum", Board.class)
			  				   .setParameter("boardNum", searchL)
			  				   .getResultList();
		   break;
		   
	   case "insertDate":
		   findCountryName = em.createQuery("select c from Board c where insertDate = :insertDate", Board.class)
		   						.setParameter("insertDate", search)
		   						.getResultList();
		   break;
		   
	   case "updateDate":
		   findCountryName = em.createQuery("select c from Board c where updateDate = :updateDate", Board.class)
		   						.setParameter("updateDate", search)
		   						.getResultList();
		   break;
		   
	   case "boardTitle":
		   findCountryName = em.createQuery("select c from Board c where boardTitle = :boardTitle", Board.class)
		   						.setParameter("boardTitle", search)
		   						.getResultList();
		   break;
		   
	   case "boardContents":
		   findCountryName = em.createQuery("select c from Board c where boardContents = :boardContents", Board.class)
		   						.setParameter("boardContents", search)
		   						.getResultList();
		   break;
		   
	   case "boardCount":
		   Long boardCountL = Long.parseLong(search);
		   findCountryName = em.createQuery("select c from Board c where boardCount = :boardCount", Board.class)
		   						.setParameter("boardCount", boardCountL)
		   						.getResultList();
		   break;
	   case "reportCount":
		   Long reportCountL = Long.parseLong(search);
		   findCountryName = em.createQuery("select c from Board c where reportCount = :reportCount", Board.class)
		   						.setParameter("reportCount", reportCountL)
		   						.getResultList();
		   break;
	   case "continent":
		   Continent continent2 = Continent.valueOf(search);
		   findCountryName = em.createQuery("select c from Board c where continent = :continent", Board.class)
				   				.setParameter("continent", continent2)
				   				.getResultList();
		   break;
	   case "boardType":
		   BoardType boardType2 = BoardType.valueOf(search);
		   findCountryName = em.createQuery("select c from Board c where boardType = :boardType", Board.class)
		   						.setParameter("boardType", boardType2)
		   						.getResultList();
		   break;
	   }
	   
	   return findCountryName;
	}
   
   // 검색한 국가 데이터 가져오기 (페이지에 해당하는 10개 국가)
   public List<Board> findSearchData(String select, String search, int nowPage) {
	  
	   List<Board> findCountryByName = new ArrayList<Board>();
	   
	   System.out.println("======================================"+select);
	   System.out.println("======================================"+search);
	   
	   switch(select) {
	   case "boardNum":
		   Long searchL = Long.parseLong(search);
		   findCountryByName = em.createQuery("select c from Board c where boardNum = :boardNum", Board.class)
			  				   .setParameter("boardNum", searchL)
			  				   .setFirstResult(nowPage * 10 - 10)
			  				   .setMaxResults(10)
			  				   .getResultList();
		   break;
		   
	   case "insertDate":
		   findCountryByName = em.createQuery("select c from Board c where insertDate = :insertDate", Board.class)
		   						.setParameter("insertDate", search)
		   						.getResultList();
		   break;
		   
	   case "updateDate":
		   findCountryByName = em.createQuery("select c from Board c where updateDate = :updateDate", Board.class)
		   						.setParameter("updateDate", search)
		   						.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
		   						.getResultList();
		   break;
		   
	   case "boardTitle":
		   findCountryByName = em.createQuery("select c from Board c where boardTitle = :boardTitle", Board.class)
		   						.setParameter("boardTitle", search)
		   						.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
		   						.getResultList();
		   break;
		   
	   case "boardContents":
		   findCountryByName = em.createQuery("select c from Board c where boardContents = :boardContents", Board.class)
		   						.setParameter("boardContents", search)
		   						.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
		   						.getResultList();
		   break;
		   
	   case "boardCount":
		   Long boardCountL = Long.parseLong(search);
		   findCountryByName = em.createQuery("select c from Board c where boardCount = :boardCount", Board.class)
		   						.setParameter("boardCount", boardCountL)
		   						.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
		   						.getResultList();
		   break;
	   case "reportCount":
		   Long reportCountL = Long.parseLong(search);
		   findCountryByName = em.createQuery("select c from Board c where reportCount = :reportCount", Board.class)
		   						.setParameter("reportCount", reportCountL)
		   						.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
		   						.getResultList();
		   break;
	   case "continent":
		   Continent continent2 = Continent.valueOf(search);
		   findCountryByName = em.createQuery("select c from Board c where continent = :continent", Board.class)
				   				.setParameter("continent", continent2)
				   				.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
				   				.getResultList();
		   break;
	   case "boardType":
		   BoardType boardType2 = BoardType.valueOf(search);
		   findCountryByName = em.createQuery("select c from Board c where boardType = :boardType", Board.class)
		   						.setParameter("boardType", boardType2)
		   						.setFirstResult(nowPage * 10 - 10)
		   						.setMaxResults(10)
		   						.getResultList();
		   break;
	   }
	   
	   return findCountryByName;
	}
	   
   
   
   // 국가 넘버로 국가 검색
   public List<Board> findDataByNum(Long boardNumL) {
		return em.createQuery("select c from Board c where boardNum = :boardNum", Board.class)
				 .setParameter("boardNum", boardNumL)
				 .getResultList();
	}
   
   // 국가 데이터 하나 찾아오기
   public Board findOneData(Long countryNum) {
	   return em.find(Board.class, countryNum);
   }
   
   // 국가 데이터 삭제
   public void deleteCountry(int boardNum) {
	   Long boardNum_ = Long.valueOf(boardNum); 
	   Board b = findOneData(boardNum_);
	   em.remove(b);
	   em.flush();
	   em.clear();
	}
// -------------------------------------- 국가 관련 쿼리문 ----------------------------------------------------
 
   


}