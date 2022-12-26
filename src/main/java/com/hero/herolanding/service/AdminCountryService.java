package com.hero.herolanding.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.CountryPaper;
import com.hero.herolanding.domain.EntranceLevel;
import com.hero.herolanding.domain.Inspection;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Paper;
import com.hero.herolanding.domain.Visa;
import com.hero.herolanding.dto.AdminCountryDTO;
import com.hero.herolanding.repository.AdminCountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminCountryService {
   
   private final AdminCountryRepository adminCountryRepository;
   
   public List<Long> findSignupDate() {
	   return adminCountryRepository.findSignUpDate();
   }
 
   
// ========================================================== 페이징처리 ==========================================================
   // 전체 데이터 조회
   public List<Country> findAllPageList() {
	   return adminCountryRepository.findAllPageList();
   }
   
   // DB에 저장된 데이터 양을 파악하여, 전체 설정할 페이지의 수를 뽑아옴(134개 저장 -> 페이지 14개)
   public int findAllPageCnt(List<Country> total) {
	   // pageTotalCnt는 전체 페이지의 갯수
	   int pageTotalCnt; 
	   
	   if(total.size() % 10 == 0) {
		   pageTotalCnt = total.size() / 10;
	   }else {
		   pageTotalCnt = total.size() / 10 + 1;
	   }
	   
	   return pageTotalCnt;
   }
   
   // 이전, 다음 버튼 메소드
   public int nextPrevPage(int pgNum, int totalPage) {
	   
	   // 현재 페이지 1페이지에서 << 누르면 1로 유지
	   // 현재 페이지 최대 페이지에서 >> 누르면 최대페이지 유지
	   if(pgNum <= 1) {
			pgNum = 1;
		}else if(pgNum > totalPage) {
			pgNum = totalPage;
		}
	   
	   return pgNum; 
   		}
   
   // 한 페이지에 담길 데이터 조회 (10개)
   public List<Country> findData(int nowPage){
	   // pgNum은 현재 내가 위치해있는 페이지 위치
	   return adminCountryRepository.findData(nowPage);
   }
   
   public List<Country> findDataCovid(int nowPage) {
		return adminCountryRepository.findData(nowPage);
	}
   
   public List<Integer> paging(int nowPage, int totalPage) {
	    // 페이지 총 갯수를 리스트로 담아주기
		List<Integer> totalPageCnt = new ArrayList<Integer>();
		
		int i = nowPage / 5;
		int j = totalPage / 5;
		int k = 1;
		
		// 전체 페이지 6보다 작을 때 (이 조건만 타면 끝)
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
// ========================================================== 페이징 처리 끝 ==========================================================
      
   
// ========================================================== 국가 정보 관리 ==========================================================
   // 검색한 결과의 전체 페이지 리스트
   public List<Country> findSearchPageList(String select,String search) {
		return adminCountryRepository.findSearchPageList(select, search);
   }
   
   // 국가 데이터 이름으로 조회 (10개)
   public List<Country> findSearchData(String select, String search, int nowPage) {
		return adminCountryRepository.findSearchData(select, search, nowPage);
	}

   // 국가 데이터 국가 번호로 조회
   public List<Country> findDataByNum(Long countryNumL){
	   return adminCountryRepository.findDataByNum(countryNumL);
   }
   
   // 찾아온 국가 데이터 DTO객체에 저장
   public AdminCountryDTO saveData(List<Country> country) {
		// 찾아온 국가들 담아줄 DTO
		AdminCountryDTO countrydto = new AdminCountryDTO();
		
		// 찾아온 국가를 빈 CountryDTO 객체에 담아주기
		countrydto.setCountryNum(country.get(0).getCountryNum());
		countrydto.setCountryName(country.get(0).getCountryName());
		countrydto.setContinent(country.get(0).getContinent());
		countrydto.setEntranceLevel(country.get(0).getEntranceLevel());
		countrydto.setEmbassyLink(country.get(0).getEmbassyLink());
		countrydto.setCountryNote(country.get(0).getCountryNote());
		
		return countrydto;
	}
   
   // 국가 데이터 수정
   @Transactional
   public void updateData(AdminCountryDTO countrydto){
	   Country c = adminCountryRepository.findOneData(countrydto.getCountryNum());
	   	
	   c.setCountryName(countrydto.getCountryName());
	   c.setContinent(countrydto.getContinent());
	   c.setEntranceLevel(countrydto.getEntranceLevel());
	   c.setEmbassyLink(countrydto.getEmbassyLink());
	   c.setCountryNote(countrydto.getCountryNote());
   }
   
   @Transactional
   public void deleteCountry(int countryNum) {
	   adminCountryRepository.deleteCountry(countryNum);
	}



   
// ========================================================== 국가 정보 관리 끝 ==========================================================
   

   
   
   
}