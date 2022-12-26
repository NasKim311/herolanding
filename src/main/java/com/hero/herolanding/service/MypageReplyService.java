package com.hero.herolanding.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Reply;
import com.hero.herolanding.repository.MypageReplyRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class MypageReplyService {
	
	private final MypageReplyRepository mypageReplyRepository;
	
	@Transactional
	public List<Reply> findreplyContent(Long memberNum){
		return mypageReplyRepository.findreplyContent(memberNum);
	}
	

}
