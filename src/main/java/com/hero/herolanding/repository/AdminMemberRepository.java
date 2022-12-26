package com.hero.herolanding.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminMemberRepository {
	
	private final EntityManager em;

	public List<Member> findAllPageList() {
		return em.createQuery("select m from Member m", Member.class)
				   .getResultList();
	}

	public List<Member> findData(int nowPage) {
		return em.createQuery("select m from Member m", Member.class)
			    .setFirstResult(nowPage * 10 - 10)
			    .setMaxResults(10)
			    .getResultList();
	}
	
	// 도시 데이터 가져오기(전체) search = 검색어 / select = 컬럼명 (countryName, continent 등등...)
		public List<Member> findSearchPageList(String select, String search) {
		
			List<Member> findMemberName = new ArrayList<Member>();
			
			switch(select) {
			case "memberNum": // 회원번호를 검색한 경우
				Long searchL = Long.parseLong(search);
				findMemberName = em.createQuery("select m from Member m where memberNum = :memberNum", Member.class)
		  				   		 		.setParameter("memberNum", searchL)
		  				   		 		.getResultList();
				break;
				
			case "memberId": // 아이디를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberId = :memberId", Member.class)
								 .setParameter("memberId", search)
								 .getResultList();
				break;
				
				
			case "memberName": // 이름을 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberName = :memberName", Member.class)
										.setParameter("memberName", search)
										.getResultList();
				break;
			
			
			case "memberNickName": // 닉네임을 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberNickName = :memberNickName", Member.class)
										.setParameter("memberNickName", search)
										.getResultList();
				break;
			
		
			case "memberPhoneNum": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberPhoneNum = :memberPhoneNum", Member.class)
										.setParameter("memberPhoneNum", search)
										.getResultList();
				break;
			
			
			case "memberAdvAgree": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberAdvAgree = :memberAdvAgree", Member.class)
										.setParameter("memberAdvAgree", search)
										.getResultList();
				break;
			
			
			case "signUpDate": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where signUpDate = :signUpDate", Member.class)
										.setParameter("signUpDate", search)
										.getResultList();
				break;
			
			
			case "memberIsjoin": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberIsjoin = :memberIsjoin", Member.class)
										.setParameter("memberIsjoin", search)
										.getResultList();
				break;
			
			}
			
			return findMemberName;
}
		
		// 검사 데이터 10개씩 나눠서 가져오기
	public List<Member> findSearchData(String select, String search, int nowPage) {
		
			List<Member> findMemberName = new ArrayList<Member>();
			
			switch(select) {
			case "memberNum": // 회원번호를 검색한 경우
				Long searchL = Long.parseLong(search);
				findMemberName = em.createQuery("select m from Member m where memberNum = :memberNum", Member.class)
		  				   		 		.setParameter("memberNum", searchL)
		  				   		 		.setFirstResult(nowPage * 10 - 10)
		  				   		 		.setMaxResults(10)
		  				   		 		.getResultList();
				break;
				
			case "memberId": // 아이디를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberId = :memberId", Member.class)
										 .setParameter("memberId", search)
										 .setFirstResult(nowPage * 10 - 10)
										 .setMaxResults(10)
										 .getResultList();
				break;
				
				
			case "memberName": // 이름을 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberName = :memberName", Member.class)
										.setParameter("memberName", search)
										.setFirstResult(nowPage * 10 - 10)
										.setMaxResults(10)
										.getResultList();
				break;
			
			
			case "memberNickName": // 닉네임을 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberNickName = :memberNickName", Member.class)
										.setParameter("memberNickName", search)
										.setFirstResult(nowPage * 10 - 10)
										.setMaxResults(10)
										.getResultList();
				break;
			
		
			case "memberPhoneNum": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberPhoneNum = :memberPhoneNum", Member.class)
										.setParameter("memberPhoneNum", search)
										.setFirstResult(nowPage * 10 - 10)
										.setMaxResults(10)
										.getResultList();
				break;
			
			
			case "memberAdvAgree": // 검사테이블의 비고를 검색한 경우
 				findMemberName = em.createQuery("select m from Member m where memberAdvAgree = :memberAdvAgree", Member.class)
										.setParameter("memberAdvAgree", search)
										.setFirstResult(nowPage * 10 - 10)
										.setMaxResults(10)
										.getResultList();
				break;
			
			
			case "signUpDate": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where signUpDate = :signUpDate", Member.class)
										.setParameter("signUpDate", search)
										.setFirstResult(nowPage * 10 - 10)
										.setMaxResults(10)
										.getResultList();
				break;
			
			
			case "memberIsjoin": // 검사테이블의 비고를 검색한 경우
				findMemberName = em.createQuery("select m from Member m where memberIsjoin = :memberIsjoin", Member.class)
										.setParameter("memberIsjoin", search)
										.setFirstResult(nowPage * 10 - 10)
										.setMaxResults(10)
										.getResultList();
				break;
			
			}
			
			return findMemberName;
		}

		// 도시 넘버로 도시 검색
		public List<Member> findDataByNum(Long memberNum) {
			return em.createQuery("select i from Member i where memberNum = :memberNum", Member.class)
					 .setParameter("memberNum", memberNum)
					 .getResultList();
		}

		// 도시 데이터 하나 찾아오기
		public Member findOneData(Long memberNum) {
			return em.find(Member.class, memberNum);
		}

		public Member deleteMember(Long memberNum) {
			Member member = findOneData(memberNum);
			
			return member;
//			em.remove(m);
//			em.flush();
//			em.clear();
		}
	
	

}
