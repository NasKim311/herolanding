package com.hero.herolanding.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import static com.hero.herolanding.domain.QMember.*;

import com.hero.herolanding.domain.Member;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MypageRepository {

	private final EntityManager em;
	JPQLQueryFactory queryFactory;

//--------<updateMemberData() / 마이페이지 회원정보 수정 메서드>-------------------------------------------------------------------------------------	
	public void update(Member updateMemberData, String loginid) {
		queryFactory = new JPAQueryFactory(em);
		
		queryFactory.update(member)
					.set(member, updateMemberData)
					.where(member.memberId.eq(loginid))
					.execute();
		
		em.clear();
		em.flush();
	}
	
} // MypageRepository class
