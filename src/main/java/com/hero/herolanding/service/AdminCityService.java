package com.hero.herolanding.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.City;
import com.hero.herolanding.dto.AdminCityDTO;
import com.hero.herolanding.repository.AdminCityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminCityService {
	
	private final AdminCityRepository adminCityRepository;

	// 전체 데이터 조회
	public List<City> findAllPageList() {
		return adminCityRepository.findAllPageList();
	}

	// DB에 저장된 데이터 양을 파악하여, 전체 페이지 수를 뽑아옴(데이터 134개 = 페이지 14개)
	public int findAllPageCnt(List<City> total) {
		
		// 전체 페이지 개수 저장
		int pageTotalCnt;
		
		if(total.size() % 10 == 0) {
			pageTotalCnt = total.size() / 10;
		}else {
			pageTotalCnt = total.size() / 10 + 1;
		}
		
		return pageTotalCnt;
	}

	public int nextPrevPage(int pgNum, int totalPage) {
		
		// 현재 페이지 1페이지에서 <<(뒤로가기) 눌러도 1로 유지
		// 현재 페이지 최대 페이지에서 >>(다음) 눌러도 최대페이지 유지
		if(pgNum <= 1) {
			pgNum = 1;
		}else if(pgNum > totalPage) {
			pgNum = totalPage;
		}
		
		return pgNum;
	}

	public List<City> findData(int nowPage) {
		// pgNum = 현재 내가 위치해있는 페이지
		return adminCityRepository.findData(nowPage);
	}

	// 밑에 표시되는 페이지 5개씩 나누는 메소드
	public List<Integer> paging(int nowPage, int totalPage) {
		// 페이지 총 개수를 리스트로 담아주기
		List<Integer> totalPageCnt = new ArrayList<Integer>();
		
		int i = nowPage / 5;
		int j = totalPage /5;
		int k = 1;
		
		// 전체 페이지 6보다 작을 때(이 조건만 타면 끝)
		if(totalPage < 6) {
			for(k = 1; k < totalPage; k++) {
				totalPageCnt.add(k);
			}
		}
		
		// 전체 페이지 6보다 클 때
				if(totalPage >= 6) {
					// 5의 배수가 아닌 페이지
					if(nowPage % 5 != 0) {
						// 현재 페이지가 마지막 페이지와 다른 줄에 있을 때
						if(i < j) {
							for(k = i * 5 + 1 ; k < i * 5 + 6; k++) {
								totalPageCnt.add(k);
							}
						}
						// 현재 페이지가 마지막 페이지와 같은 줄에 있을 때
						if(i == j) {
							for(k = i * 5 + 1 ; k <= totalPage; k++) {
								totalPageCnt.add(k);
							}
						}
					}
					// 5의 배수인 페이지
					if(nowPage % 5 == 0) {
						// 현재 페이지가 6보다 작을 때
						if(nowPage < 6) {
							for(k = 1; k < 6; k++) {
								totalPageCnt.add(k);
							}
						}
						
						// 현재 페이지가 6보다 클 때
						if(nowPage >= 6) {
							// 현재 페이지가 마지막 페이지와 다른 줄에 있을 때
							if(i < j) {
								for(k = (i - 1) * 5 + 1 ; k < i * 5 + 6; k++) {
									totalPageCnt.add(k);
								}
							}
							
							// 현재 페이지가 마지막 페이지와 같은 줄에 있을 때
							else if(i == j) {
								// 현재 페이지가 마지막 페이지일 때
								if(nowPage == totalPage) {
									for(k = (i - 1) * 5 + 1 ; k <= totalPage; k++) {
										totalPageCnt.add(k);
									}
								}
								// 현재 페이지가 마지막 페이지와 같은 줄에 있지만, 현재페이지가 마지막 페이지는 아닐 때
								if(nowPage < totalPage) {
									for(k = (i - 1) * 5 + 1 ; k <= i * 5; k++) {
										totalPageCnt.add(k);
									}
								}
							}
						}
					}
				}
		
		return totalPageCnt;
	}
	
	// 검색한 결과의 전체 페이지 리스트
	public List<City> findSearchPageList(String select, String search) {
		return adminCityRepository.findSearchPageList(select, search);
	}

	// 도시 데이터 도시 이름으로 조회
	public List<City> findSearchData(String select, String search, int nowPage) {
		return adminCityRepository.findSearchData(select, search, nowPage);
	}

	// 도시 데이터 도시 번호로 조회
	public List<City> findDataByNum(Long cityNumL) {
		return adminCityRepository.findDataByNum(cityNumL);
	}

	// 찾아온 도시 데이터 DTO객체에 저장
	public AdminCityDTO saveData(List<City> city) {
		// 찾아온 도시를 담아줄 DTO객체
		AdminCityDTO citydto = new AdminCityDTO();
		
		citydto.setCityNum(city.get(0).getCityNum());
		citydto.setCityName(city.get(0).getCityName());
		citydto.setEntranceLevel(city.get(0).getEntranceLevel());
		
		return citydto;
		
	}
	
// 도시 데이터 수정
	@Transactional
	public void updateData(AdminCityDTO citydto) {
		City c = adminCityRepository.findOneData(citydto.getCityNum());
		
		c.setCityName(citydto.getCityName());
		c.setEntranceLevel(citydto.getEntranceLevel());
	}

// 도시 데이터 삭제
	public void deleteCity(int cityNum) {
		adminCityRepository.deleteCity(cityNum);
	}
	

}














