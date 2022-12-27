package com.hero.herolanding.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Reply;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.hero.herolanding.domain.QReply.*;		// 쓰기위해 임포트 해줘야함
import static com.hero.herolanding.domain.QBoard.*;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MypageReplyRepository {
	
	private final EntityManager em;
	JPQLQueryFactory queryFactory;

	public List<Reply> findreplyContent(Long memberNum){
		
		queryFactory = new JPAQueryFactory(em);
		List<Reply> content = queryFactory.selectFrom(reply)
//							.leftJoin(board)
							.where(reply.member.memberNum.eq(memberNum))
							.fetch();
		return content;
		
		
	}
	
	
	
	
	
	
	
	
}
