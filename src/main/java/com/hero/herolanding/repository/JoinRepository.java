package com.hero.herolanding.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.JoinDTO;

import lombok.RequiredArgsConstructor;

@Repository @RequiredArgsConstructor
public class JoinRepository {

	@Autowired
	private final EntityManager em;
	private static long sequence = 0L;
	
		// 저장
		public void save(Member member) {
			em.persist(member);
		}
		
		
		// 가입일자 저장
		public void saveSignUpDate(Member member) {
			member.setSignUpDate(LocalDate.now());
			em.persist(member);
		}
		
		// 아이디 찾기
		public List<Member> findDuplicationId(String memberId){
		
			 return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class)
					 .setParameter("memberId", memberId)
					 .getResultList();
		}
		
		// 닉네임 찾기
		public List<Member> findDuplicationNickName(String memberNickName){
		
			 return em.createQuery("select m from Member m where m.memberNickName = :memberNickName", Member.class)
					 .setParameter("memberNickName", memberNickName)
					 .getResultList();
		}
		
		// 이메일 찾기
				public List<Member> findDuplicationEmail(String memberEmail){
				
					 return em.createQuery("select m from Member m where m.memberEmail = :memberEmail", Member.class)
							 .setParameter("memberEmail", memberEmail)
							 .getResultList();
				}
		
		
	
}
