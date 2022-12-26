package com.hero.herolanding.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hero.herolanding.domain.Visa;
import com.hero.herolanding.dto.AdminPaperDTO;
import com.hero.herolanding.dto.AdminVisaDTO;
import com.hero.herolanding.service.AdminVisaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminVisaController {
	
	private final AdminVisaService adminVisaService;



// DB관리 메인 페이지
	@GetMapping("/DB비자관리/visa/{pgNum}")
	public String db(@PathVariable("pgNum") int pgNum,
					 Model model) {
		// 전체 페이지 리스트
		List<Visa> total = adminVisaService.findAllPageList();
		
		// 전체 페이지 갯수
		int totalPage = adminVisaService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminVisaService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Visa> visa = adminVisaService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminVisaService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		// 빈 DTO 객체
		model.addAttribute("get", get);
		model.addAttribute("visa", visa);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		return "admin/DB비자관리";
	}

// DB 검색 form 날리기
	@PostMapping("/DB비자관리/visa/{pgNum}")
	public String dbSearch(@PathVariable("pgNum") int pgNum,
						   @RequestParam("select") String select,
						   @RequestParam("search") String search,
						   Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		pgNum = 1;
		
		// 검색한 결과의 전체 페이지 리스트
		List<Visa> total = adminVisaService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminVisaService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminVisaService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<Visa> visa = adminVisaService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminVisaService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		// 검색 결과만 넘김
		model.addAttribute("visa", visa);
		
		return "admin/DB비자관리";
	}
	
// DB 검색 form 날리고 페이지이동
	@PostMapping("/DB비자관리/visa/{pgNum}/paging")
	public String dbSearchPaging(@PathVariable("pgNum") int pgNum,
			@RequestParam("select") String select,
			@RequestParam("search") String search,
			Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		//pgNum = 1;
		
		// 검색한 결과의 전체 페이지 리스트
		List<Visa> total = adminVisaService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminVisaService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminVisaService.nextPrevPage(pgNum, totalPage);
		
		// 뷰 단에서 입력받은 페이지에 담길 국가 10개씩 뽑아오기
		List<Visa> visa = adminVisaService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminVisaService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		// 검색 결과만 넘김
		model.addAttribute("visa", visa);
		
		return "admin/DB비자관리";
	}

// DB수정 페이지 이동
	@GetMapping("/DB비자수정/visa/{visaNum}")
	public String dbupdate(@PathVariable("visaNum") int visaNum,
						   Model model) {
		
		// 해당 국가번호의 국가 찾아오기
		List<Visa> visa= adminVisaService.findDataByNum(visaNum);

		// 찾아온 국가 DTO에 담아주기
		AdminVisaDTO visadto = adminVisaService.saveData(visa);
		
		model.addAttribute("visadto", visadto);
		
		return "admin/DB비자수정";
	}
	
// DB 수정 form 날리기
	@PostMapping("/DB비자수정/visa/{visaNum}")
	public String dbupdatePost(@PathVariable("visaNum") int visaNum,
							   @ModelAttribute("visadto") AdminVisaDTO visadto,
							   Model model) {
		
		adminVisaService.updateData(visadto);
		
		// 관리페이지로 넘어갈 때 기본 1페이지로 이동
		int pgNum = 1;
		
		// 전체 페이지 리스트
		List<Visa> total = adminVisaService.findAllPageList();
		
		// 전체 페이지 갯수
		int totalPage = adminVisaService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminVisaService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Visa> visa = adminVisaService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminVisaService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("visa", visa);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);

		return "admin/DB비자관리";
	}
	
// 국가 삭제 이후 페이지 이동
	@GetMapping("/DB비자삭제/visa/{visaNum}")
	public String deleteData(@PathVariable("visaNum") int visaNum) {
		
		adminVisaService.deleteData(visaNum);
		
		return "redirect:/admin";
	}

}
