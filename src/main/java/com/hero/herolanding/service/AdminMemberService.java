package com.hero.herolanding.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.AdminMemberDTO;
import com.hero.herolanding.repository.AdminMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

	private final AdminMemberRepository adminMemberRepository;

	// 전체 회원 데이터 조회
	public List<Member> findAllPageList() {
		return adminMemberRepository.findAllPageList();
	}

	// DB에 저장된 데이터 양을 파악하여, 전체 페이지 수를 뽑아옴(데이터 134개 = 페이지 14개)
	public int findAllPageCnt(List<Member> total) {
		
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

	public List<Member> findData(int nowPage) {
		// pgNum = 현재 내가 위치해있는 페이지
		return adminMemberRepository.findData(nowPage);
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
		public List<Member> findSearchPageList(String select, String search) {
			return adminMemberRepository.findSearchPageList(select, search);
		}

		// 회원 데이터 회원 이름으로 조회
		public List<Member> findSearchData(String select, String search, int nowPage) {
			return adminMemberRepository.findSearchData(select, search, nowPage);
		}

		// 회원 데이터 회원 번호로 조회
		public List<Member> findDataByNum(Long memberNum) {
			Long memberNumL = Long.valueOf(memberNum);
			return adminMemberRepository.findDataByNum(memberNumL);
		}

		// 찾아온 회원 데이터 DTO객체에 저장
		public AdminMemberDTO saveData(List<Member> member) {
			// 찾아온 회원 정보를 담아줄 DTO객체
			AdminMemberDTO adminmemberdto = new AdminMemberDTO();
			
			adminmemberdto.setMemberNum(member.get(0).getMemberNum());
			adminmemberdto.setMemberId(member.get(0).getMemberId());
			adminmemberdto.setMemberName(member.get(0).getMemberName());
			adminmemberdto.setMemberNickName(member.get(0).getMemberNickName());
			adminmemberdto.setMemberPhoneNum(member.get(0).getMemberPhoneNum());
			adminmemberdto.setMemberEmail(member.get(0).getMemberEmail());
			adminmemberdto.setMemberAdvAgree(member.get(0).isMemberAdvAgree());
			adminmemberdto.setSignUpDate(member.get(0).getSignUpDate());
			adminmemberdto.setMemberIsjoin(member.get(0).isMemberIsjoin());
			
			return adminmemberdto;
			
		}
		
	// 멤버 데이터 수정
		@Transactional
		public void updateData(AdminMemberDTO adminmemberdto) {
			Member m = adminMemberRepository.findOneData(adminmemberdto.getMemberNum());
			
			if(adminmemberdto.isMemberIsjoin() == true) {
				m.setMemberIsjoin(false);
			}else if(adminmemberdto.isMemberIsjoin() == false) {
				m.setMemberIsjoin(true);
			}
			
		}

}

















