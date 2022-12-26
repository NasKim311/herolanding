package com.hero.herolanding.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hero.herolanding.domain.Paper;
import com.hero.herolanding.dto.AdminPaperDTO;
import com.hero.herolanding.service.AdminPaperService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminPaperController {
	
	private final AdminPaperService adminPaperService;



// DB관리 메인 페이지
	@GetMapping("/DB서류관리/paper/{pgNum}")
	public String db(@PathVariable("pgNum") int pgNum,
					 Model model) {
		// 전체 페이지 리스트
		List<Paper> total = adminPaperService.findAllPageList();
		
		// 전체 페이지 갯수
		int totalPage = adminPaperService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminPaperService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Paper> paper = adminPaperService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminPaperService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("paper", paper);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		return "admin/DB서류관리";
	}

// DB 검색 form 날리기
	@PostMapping("/DB서류관리/paper/{pgNum}")
	public String dbSearch(@PathVariable("pgNum") int pgNum,
						   @RequestParam("select") String select,
						   @RequestParam("search") String search,
						   Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		pgNum = 1;
		
		System.out.println("========================" + select);
		System.out.println("========================" + search);
		// 검색한 결과의 전체 페이지 리스트
		List<Paper> total = adminPaperService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminPaperService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminPaperService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<Paper> paper = adminPaperService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminPaperService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		// 검색 결과만 넘김
		model.addAttribute("paper", paper);
		
		return "admin/DB서류관리";
	}
	
// DB 검색 form 날리고 페이지이동
	@PostMapping("/DB서류관리/paper/{pgNum}/paging")
	public String dbSearchPaging(@PathVariable("pgNum") int pgNum,
			@RequestParam("select") String select,
			@RequestParam("search") String search,
			Model model) {
		// 검색한 결과의 전체 페이지 리스트
		List<Paper> total = adminPaperService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminPaperService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminPaperService.nextPrevPage(pgNum, totalPage);
		
		// 뷰 단에서 입력받은 페이지에 담길 국가 10개씩 뽑아오기
		List<Paper> paper = adminPaperService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminPaperService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		// 검색 결과만 넘김
		model.addAttribute("paper", paper);
		
		return "admin/DB서류관리";
	}

// DB수정 페이지 이동
	@GetMapping("/DB서류수정/paper/{paperNum}")
	public String dbupdate(@PathVariable("paperNum") int paperNum,
						   Model model) {
		
		// 해당 국가번호의 국가 찾아오기
		List<Paper> paper= adminPaperService.findDataByNum(paperNum);

		// 찾아온 국가 DTO에 담아주기
		AdminPaperDTO paperdto = adminPaperService.saveData(paper);
		
		model.addAttribute("paperdto", paperdto);
		model.addAttribute("paperNum", paperNum);
		return "admin/DB서류수정";
	}
	
// DB 수정 form 날리기
	@PostMapping("/DB서류수정/paper/{paperNum}")
	public String dbupdatePost(@PathVariable("paperNum") int paperNum,
							   @ModelAttribute("paperdto") AdminPaperDTO paperdto,
							   Model model) {
		
		adminPaperService.updateData(paperdto);
		// 관리페이지로 넘어갈 때 기본 1페이지로 이동
		int pgNum = 1;
		
		// 전체 페이지 리스트
		List<Paper> total = adminPaperService.findAllPageList();
		
		// 전체 페이지 갯수
		int totalPage = adminPaperService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminPaperService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Paper> paper = adminPaperService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminPaperService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("paper", paper);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);

		return "admin/DB서류관리";
	}
	
// 국가 삭제 이후 페이지 이동
	@GetMapping("/DB서류삭제/paper/{visaNum}")
	public String deleteData(@PathVariable("visaNum") int visaNum) {
		
		adminPaperService.deleteData(visaNum);
		
		return "redirect:/admin";
	}

}
