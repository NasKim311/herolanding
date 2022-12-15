package com.hero.herolanding.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeRepository {

	
	private final EntityManager em;
	
	// 메인페이지에서 사용
	
	// 전 세계 환율 정보 (크롤링) - 실시간 변동 (인서트)
	public void ExchageInsert() {
		
	}
	// 전 세계 환율 정보 (크롤링) - 실시간 변동 (업데이트)
	public void ExchageUpdate() {
		
	}
	// 등록된 회원의 많이 여행가는 나라 순위 (디비 - select)
	public void maxCntContry() {
		
	}
	// 코로나 확진자 수 순위 (크롤링) - 매일 변동 (인서트)
	public void covidInsert() {
		
	}
	// 코로나 확진자 수 순위 (크롤링) - 매일 변동 (업데이트)
	public void covidUpdate() {
		
	}

	// 검색 후 사용
	// 검색한 나라의 정보 가져오기 ( 환율 , 코로나 수 , 필요 서류 , 검사 종류 , 여행자 순위 )
	// DTO를 여러 테이블 조인해서 작성해도 되는지? 안된다면 전부 셀렉트 만들기.
	public void findCounrty() {
	
	}
	// 환율정보
	public void findExchage() { 
		
	}
	// 코로나 수 
	public void findCovid() {
		
	}
	// 필요 서류
	public void findCountryPeper() {
		
	}
	// 검사 종류
	public void findCheck() {
		
	}
	// 여행자 순위
	public void findTripLank() {
		
	}
	
	
}
