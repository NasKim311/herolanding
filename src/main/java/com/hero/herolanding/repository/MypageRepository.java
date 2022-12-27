package com.hero.herolanding.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import static com.hero.herolanding.domain.QMember.*;
import static com.hero.herolanding.domain.QBoard.*;

import java.util.List;

import com.hero.herolanding.domain.Board;
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
	public void updateMemberData(Member updateMemberData, String loginid) {
		queryFactory = new JPAQueryFactory(em);
		
		queryFactory.update(member)
					.set(member.memberPw, updateMemberData.getMemberPw())
					.set(member.memberName, updateMemberData.getMemberName())
					.set(member.memberNickName, updateMemberData.getMemberNickName())
					.set(member.memberEmail, updateMemberData.getMemberEmail())
					.set(member.memberPhoneNum, updateMemberData.getMemberPhoneNum())
					.where(member.memberId.eq(loginid))
					.execute();
		
		em.clear();
		em.flush();
	}
	
//--------<deleteMemberData() / 회원정보 삭제 메서드>-------------------------------------------------------------------------------------	
	public void deleteMemberData(String loginId) {
		queryFactory = new JPAQueryFactory(em);
		
		System.out.println("R" + loginId);
		queryFactory.update(member)
					.set(member.memberIsjoin, 1L)
					.where(member.memberId.eq(loginId))
					.execute();
		
		em.clear();
		em.flush();
	}

//--------<BoardCountByMemberId() / 해당 아이디로 작성한 모든 게시글들을 리턴하는 메서드>-------------------------------------------------------------------------------------	
	public List<Board> BoardCountByMemberId(String loginId) {
		return queryFactory.selectFrom(board).where(board.member.memberId.eq(loginId)).fetch();
	}
	
	
	
} // MypageRepository class
