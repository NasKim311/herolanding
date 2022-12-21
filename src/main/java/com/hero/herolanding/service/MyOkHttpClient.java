package com.hero.herolanding.service;

public class MyOkHttpClient { // 카카오 액세스 토큰을 받아오기 위한 클래스

	private static String baseURL = "https://kauth.kakao.com";
	private static String appKey = "df7362e9e7104a8b6832b605968c7eae";
	private static String redirectURI = "http://localhost:9998/kakao/loginForm";

	private MyOkHttpClient() {
	}
	
	public static void getAccessToken(String code) {
		
	}
	
	
	
} // MyOkHttpClient class
