package com.hero.herolanding.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.EntranceLevel;
import com.hero.herolanding.domain.Inspection;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminInspectionRepository {
	
	private final EntityManager em;
	
	// 도시 테이블 가져오기 (도시 전체 찾기)
	public List<Inspection> findAllPageList() {
		return em.createQuery("select i from Inspection i", Inspection.class)
				   .getResultList();
	}
	
	// 도시 테이블 10개씩 잘라서 가져오기
	public List<Inspection> findData(int nowPage) {
		return em.createQuery("select i from Inspection i", Inspection.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
	}

	// 도시 데이터 가져오기(전체) search = 검색어 / select = 컬럼명 (countryName, continent 등등...)
	public List<Inspection> findSearchPageList(String select, String search) {
		int searchInt;
		List<Inspection> findInspectionName = new ArrayList<Inspection>();
		
		switch(select) {
		case "inspectionNum": // 검사번호를 검색한 경우
			searchInt = Integer.parseInt(search);
			findInspectionName = em.createQuery("select i from Inspection i where i.inspectionNum = :inspectionNum", Inspection.class)
	  				   		 		.setParameter("inspectionNum", searchInt)
	  				   		 		.getResultList();
			break;
			
		case "isolationPeriod": // 격리기간을 검색한 경우
			findInspectionName = em.createQuery("select i from Inspection i where i.isolationPeriod = :isolationPeriod", Inspection.class)
							 .setParameter("isolationPeriod", search)
							 .getResultList();
			break;
			
		case "inspectionCount": // 검사횟수를 검색한 경우
			findInspectionName = em.createQuery("select i from Inspection i where i.inspectionCount = :inspectionCount", Inspection.class)
							   .setParameter("inspectionCount", search)
							   .getResultList();
			break;
			
		case "inspectionNote": // 검사테이블의 비고를 검색한 경우
			findInspectionName = em.createQuery("select i from Inspection i where i.inspectionNote = :inspectionNote", Inspection.class)
									.setParameter("inspectionNote", search)
									.getResultList();
			break;
		}
		
		return findInspectionName;
	}

	// 검사 데이터 10개씩 나눠서 가져오기
public List<Inspection> findSearchData(String select, String search, int nowPage) {
	
		List<Inspection> findInspectionName = new ArrayList<Inspection>();
		
		switch(select) {
		case "inspectionNum": // 검사번호를 검색한 경우
			Integer searchInt = Integer.parseInt(search);
			findInspectionName = em.createQuery("select i from Inspection i where inspectionNum = :inspectionNum", Inspection.class)
	  				   		 		.setParameter("inspectionNum", searchInt)
	  				   		 		.setFirstResult(nowPage * 10 - 10)
	  				   		 		.setMaxResults(10)
	  				   		 		.getResultList();
			break;
			
		case "isolationPeriod": // 격리기간을 검색한 경우
			findInspectionName = em.createQuery("select i from Inspection i where isolationPeriod = :isolationPeriod", Inspection.class)
							 		.setParameter("isolationPeriod", search)
							 		.setFirstResult(nowPage * 10 - 10)
							 		.setMaxResults(10)
							 		.getResultList();
			break;
			
		case "inspectionCount": // 검사횟수를 검색한 경우
			findInspectionName = em.createQuery("select i from Inspection i where inspectionCount = :inspectionCount", Inspection.class)
									.setParameter("inspectionCount", search)
									.setFirstResult(nowPage * 10 - 10)
 				   		 			.setMaxResults(10)
 				   		 			.getResultList();
			break;
			
		case "inspectionNote": // 검사테이블의 비고를 검색한 경우
			findInspectionName = em.createQuery("select i from Inspection i where inspectionNote = :inspectionNote", Inspection.class)
									.setParameter("inspectionNote", search)
									.setFirstResult(nowPage * 10 - 10)
	  				   		 		.setMaxResults(10)
									.getResultList();
			break;
		}
		
		return findInspectionName;
	}

	// 도시 넘버로 도시 검색
	public List<Inspection> findDataByNum(int inspectionNum) {
		return em.createQuery("select i from Inspection i where inspectionNum = :inspectionNum", Inspection.class)
				 .setParameter("inspectionNum", inspectionNum)
				 .getResultList();
	}

	// 도시 데이터 하나 찾아오기
	public Inspection findOneData(int inspectionNum) {
		return em.find(Inspection.class, inspectionNum);
	}

	public void deleteInspection(int inspectionNum) {
		Inspection i = findOneData(inspectionNum);
		em.remove(i);
		em.flush();
		em.clear();
	}

}
















