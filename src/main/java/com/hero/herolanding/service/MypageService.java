package com.hero.herolanding.service;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.repository.MypageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageService {

//--------<updateMemberData() / 마이페이지 회원정보 수정 메서드>-------------------------------------------------------------------------------------	
	private final MypageRepository mypageRepository;

	public Member update(Member updateMemberData) {
	
		mypageRepository.update(updateMemberData, updateMemberData.getMemberId());
		
		return null;
	}
	
} // MypageService class
