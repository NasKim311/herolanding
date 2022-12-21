package com.hero.herolanding.service;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.repository.MypageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageService {

	private final MypageRepository mypageRepository;

	public Member update(Member updateMemberData) {
		return null;
	}
	
} // MypageService class
