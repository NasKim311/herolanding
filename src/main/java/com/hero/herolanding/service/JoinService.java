package com.hero.herolanding.service;

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
	
}
