package com.hero.herolanding.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
}
