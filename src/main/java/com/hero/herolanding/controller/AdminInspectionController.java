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
import com.hero.herolanding.domain.Inspection;
import com.hero.herolanding.dto.AdminCityDTO;
import com.hero.herolanding.dto.AdminInspectionDTO;
import com.hero.herolanding.service.AdminInspectionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminInspectionController {
	
	private final AdminInspectionService adminInspectionService;

// DB 검사관리 메인페이지
	@GetMapping("/DB검사관리/inspection/{pgNum}")
	public String db(@PathVariable("pgNum") int pgNum, Model model) {
		
		// 전체 페이지 리스트
		List<Inspection> total = adminInspectionService.findAllPageList();
		
		// 전체 페이지 개수
		int totalPage = adminInspectionService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminInspectionService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Inspection> inspection = adminInspectionService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminInspectionService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("inspection", inspection);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		return "admin/DB검사관리";
	}
	
// DB 검색 form 날리기
	@PostMapping("/DB검사관리/inspection/{pgNum}")
	public String dbSearch( @PathVariable("pgNum") int pgNum,
							@RequestParam("select") String select,
							@RequestParam("search") String search,
							Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		pgNum = 1;
		
		// 검색한 결과이 전체 페이지 리스트
		List<Inspection> total = adminInspectionService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminInspectionService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminInspectionService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<Inspection> inspection = adminInspectionService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminInspectionService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		// 검색 결과만 넘김
		model.addAttribute("inspection", inspection);
		
		return "admin/DB검사관리";
	}
	
// DB 검색 form 날리고 페이지이동
	@PostMapping("/DB검사관리/inspection/{pgNum}/paging")
	public String dbSearchPaging( @PathVariable("pgNum") int pgNum,
							@RequestParam("select") String select,
							@RequestParam("search") String search,
							Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		//pgNum = 1;
		
		// 검색한 결과이 전체 페이지 리스트
		List<Inspection> total = adminInspectionService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminInspectionService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminInspectionService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<Inspection> inspection = adminInspectionService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminInspectionService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		// 검색 결과만 넘김
		model.addAttribute("inspection", inspection);
		
		return "admin/DB검사관리";
	}
			
// DB수정 페이지 이동(검사수정)
	@GetMapping("/DB검사수정/inspection/{inspectionNum}")
	public String dbupdate(@PathVariable("inspectionNum") int inspectionNum, Model model) {
		
		// 해당 검사번호의 검사 찾아오기
		List<Inspection> inspection = adminInspectionService.findDataByNum(inspectionNum);
		
		// 찾아온 검사를 DTO에 담아주기
		AdminInspectionDTO inspectiondto = adminInspectionService.saveData(inspection);
		
		model.addAttribute("inspectiondto", inspectiondto);
		
		return "admin/DB검사수정";
	}
	
// DB 수정 form 날리기
	@PostMapping("/DB검사수정/inspection/{inspectionNum}")
	public String dbupdatePost(	@PathVariable("inspectionNum") int inspectionNum,
								@ModelAttribute("inspectiondto") AdminInspectionDTO inspectiondto,
								Model model) {
		adminInspectionService.updateData(inspectiondto);
		
		// 관리자 페이지로 넘어갈 때 기본 페이지 1로 이동
		int pgNum = 1;
		
		// 전체 페이지 리스트
		List<Inspection> total = adminInspectionService.findAllPageList();
		
		// 전체 페이지 개수
		int totalPage = adminInspectionService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminInspectionService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Inspection> inspection = adminInspectionService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminInspectionService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("inspection", inspection);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
				
		return "admin/DB검사관리";
	}
	
// 도시 삭제 이후 페이지 이동
	@GetMapping("/DB검사삭제/inspection/{inspectionNum}")
	public String deleteInspection(@PathVariable("inspectionNum") int inspectionNum) {
		adminInspectionService.deleteInspection(inspectionNum);
		
		return "redirect:/admin";
	}
	
	
	
}



















