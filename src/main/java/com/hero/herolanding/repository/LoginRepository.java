package com.hero.herolanding.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Member;

import static com.hero.herolanding.domain.QMember.*;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

	private final EntityManager em;
	JPQLQueryFactory queryFactory;

//--------<addTestMemberDate() / 테스트 멤버 데이터를 인서트하는 메서드 >-------------------------------------------------------------------------------------	
	public void addTestMemberDate() {
		Member member = new Member();
		member.setMemberId("test");
		member.setMemberPw("1234");
		em.persist(member);
	}

//--------<findAllMemberList() / 모든 멤버 정보를 리스트로 리턴하는 메서드 >-------------------------------------------------------------------------------------	
	public List<Member> findAllMemberList() {

		List<Member> allMemberList = queryFactory.selectFrom(member).fetch();

		return allMemberList;
	}

//--------<findByLoginId() / 파라미터로 받아온 loginId에 해당하는 멤버 정보를 리턴하는 메서드 >-------------------------------------------------------------------------------------	
	public Member findByLoginId(String loginId, String loginPw) {
		queryFactory = new JPAQueryFactory(em);

		Member loginMemberData = queryFactory.selectFrom(member)
				.where(member.memberId.eq(loginId), member.memberPw.eq(loginPw)).fetchOne();

		return loginMemberData;
	}

} // LoginRepository class
