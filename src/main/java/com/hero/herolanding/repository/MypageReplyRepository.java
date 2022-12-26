package com.hero.herolanding.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Reply;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MypageReplyRepository {
	
	@Autowired
	private final EntityManager em;

	public List<Reply> findreplyContent(Long memberNum){
		return 	em.createQuery("select r from REPLY r where member.memberNum = memberNum", Reply.class )
				.setParameter("memberNum", memberNum)
				.getResultList();
	}
}
