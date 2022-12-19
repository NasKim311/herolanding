package com.hero.herolanding.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final LoginRepository loginRepository;

//--------<login() / 로그인폼에서 입력한 값과 DB의 값이 같은지 비교하는 메서드 >-------------------------------------------------------------------------------------	
	@Transactional
	public Member login(String loginId, String loginPw) {

		Member loginMemberData = loginRepository.findByLoginId(loginId, loginPw);

		return loginMemberData;
	}

} // LoginService class
