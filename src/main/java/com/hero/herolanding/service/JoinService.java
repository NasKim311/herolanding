package com.hero.herolanding.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.JoinDTO;
import com.hero.herolanding.repository.JoinRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class JoinService {

	private final JoinRepository joinRepository;
	
	// member값 넘기기
	@Transactional
	public void saveJoin(Member member ) {
		joinRepository.save(member); 
		joinRepository.saveSignUpDate(member);
	}
	
	// 중복아이디값 찾기
	@Transactional
	public List<Member> findId(String memberId) {
		return joinRepository.findDuplicationId(memberId);
	}
	
	// 중복닉네임값 찾기
	@Transactional
	public List<Member> findNickName(String memberNickName) {
		return joinRepository.findDuplicationNickName(memberNickName);
	}
	
	// 중복이메일 찾기
		@Transactional
		public List<Member> findEmail(String memberEmail) {
			return joinRepository.findDuplicationEmail(memberEmail);
		}
	
}
