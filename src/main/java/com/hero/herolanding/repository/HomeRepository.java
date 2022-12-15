package com.hero.herolanding.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeRepository {

	private final EntityManager em;
	
	// 전 세계 환율 정보 (크롤링) - 실시간 변동 (인서트)
	
	
	// 전 세계 환율 정보 (크롤링) - 실시간 변동 (업데이트)
	// 등록된 회원의 많이 여행가는 나라 순위 (디비 - select)
	// 코로나 확진자 수 순위 (크롤링) - 매일 변동 (인서트)
	// 코로나 확진자 수 순위 (크롤링) - 매일 변동 (업데이트)

	// 검색한 나라의 정보 가져오기 ( 환율 , 코로나 수 , 필요 서류 , 검사 종류 , 여행자 순위 )
	// 환율정보
	// 코로나 수 
	// 필요 서류
	// 검사 종류
	// 여행자 순위
	// 해당 국가 뉴스기사 ( 크롤링 , 디비 X )
	
	
}
