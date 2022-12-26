package com.hero.herolanding.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hero.herolanding.domain.City;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.AdminMemberDTO;
import com.hero.herolanding.service.AdminMemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

	private final AdminMemberService adminMemberService;
	
	// 회원관리.html
	
	// 회원관리 메인페이지
	@GetMapping("/회원관리/{pgNum}")
	public String db(@PathVariable("pgNum") int pgNum, Model model) {
		// 전체 페이지 리스트
		List<Member> total = adminMemberService.findAllPageList();
		
		// 전체 페이지 개수
		int totalPage = adminMemberService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminMemberService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Member> member = adminMemberService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminMemberService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("member", member);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		return "admin/회원관리";
	}
	
// DB 검색 form 날리기
	@PostMapping("/회원관리/{pgNum}")
	public String dbSearch( @PathVariable("pgNum") int pgNum,
							@RequestParam("select") String select,
							@RequestParam("search") String search,
							Model model) {
	// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
	pgNum = 1;
	
	// 검색한 결과이 전체 페이지 리스트
	List<Member> total = adminMemberService.findSearchPageList(select, search);
	
	// 전체 페이지 숫자
	int totalPage = adminMemberService.findAllPageCnt(total);
	
	// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
	int nowPage = adminMemberService.nextPrevPage(pgNum, totalPage);
	
	// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
	List<Member> member = adminMemberService.findSearchData(select, search, nowPage);
	if(member.size() == 0) {
		member.add(new Member());
	}
	
	// 전체 페이징 메소드
	List<Integer> totalPageCnt = adminMemberService.paging(nowPage, totalPage);
	
	// 페이징 부분을 결정하는 파라미터
	String post = "post";
	
	model.addAttribute("post", post);
	model.addAttribute("select", select);
	model.addAttribute("search", search);
	model.addAttribute("totalPageCnt", totalPageCnt);
	model.addAttribute("nowPage", nowPage);
	
	// 검색 결과만 넘김
	model.addAttribute("member", member);
	
	return "admin/회원관리";
}

// DB 검색 form 날리고 페이지이동
	@PostMapping("/회원관리/{pgNum}/paging")
	public String dbSearchPaging( @PathVariable("pgNum") int pgNum,
							@RequestParam("select") String select,
							@RequestParam("search") String search,
							Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		//pgNum = 1;
		
		// 검색한 결과이 전체 페이지 리스트
		List<Member> total = adminMemberService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminMemberService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminMemberService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<Member> member = adminMemberService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminMemberService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		// 검색 결과만 넘김
		model.addAttribute("member", member);
		
		return "admin/회원관리";
	}
			
// DB수정 페이지 이동(회원수정)
	@GetMapping("/회원수정/{pgNum}")
	public String dbupdate(@PathVariable("pgNum") int pgNum, Model model) {
		Long memberNumL=Long.valueOf(pgNum);
		
		// 해당 검사번호의 검사 찾아오기
		List<Member> member = adminMemberService.findDataByNum(memberNumL);
		
		// 찾아온 검사를 DTO에 담아주기
		AdminMemberDTO memberdto = adminMemberService.saveData(member);
		
		model.addAttribute("memberdto", memberdto);
		
		return "admin/회원정보수정";
	}
	
// DB 수정 form 날리기
	@PostMapping("/회원수정/{pgNum}")
	public String dbupdatePost(	@PathVariable("pgNum") int pgNum,
								@ModelAttribute("memberdto") AdminMemberDTO memberdto,
								Model model) {
		adminMemberService.updateData(memberdto);
		
		// 관리자 페이지로 넘어갈 때 기본 페이지 1로 이동
		pgNum = 1;
		
		// 전체 페이지 리스트
		List<Member> total = adminMemberService.findAllPageList();
		
		// 전체 페이지 개수
		int totalPage = adminMemberService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminMemberService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Member> member = adminMemberService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminMemberService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("member", member);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
				
		return "admin/회원관리";
	}
	
}
