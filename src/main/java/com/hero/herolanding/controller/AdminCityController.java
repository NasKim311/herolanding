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
import com.hero.herolanding.dto.AdminCityDTO;
import com.hero.herolanding.service.AdminCityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminCityController {
	
	private final AdminCityService adminCityService;

// DB 도시관리 메인페이지
	@GetMapping("/DB도시관리/city/{pgNum}")
	public String db(@PathVariable("pgNum") int pgNum, Model model) {
		
		// 전체 페이지 리스트
		List<City> total = adminCityService.findAllPageList();
		
		// 전체 페이지 개수
		int totalPage = adminCityService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminCityService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<City> city = adminCityService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminCityService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("city", city);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		return "admin/DB도시관리";
	}
	
// DB 검색 form 날리기
	@PostMapping("/DB도시관리/city/{pgNum}")
	public String dbSearch( @PathVariable("pgNum") int pgNum,
							@RequestParam("select") String select,
							@RequestParam("search") String search,
							Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		pgNum = 1;
		
		// 검색한 결과이 전체 페이지 리스트
		List<City> total = adminCityService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminCityService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminCityService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<City> city = adminCityService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminCityService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		// 검색 결과만 넘김
		model.addAttribute("city", city);
		
		return "admin/DB도시관리";
	}
	
// DB 검색 form 날리고 페이지이동
	@PostMapping("/DB도시관리/city/{pgNum}/paging")
	public String dbSearchPaging( @PathVariable("pgNum") int pgNum,
							@RequestParam("select") String select,
							@RequestParam("search") String search,
							Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		//pgNum = 1;
		
		// 검색한 결과이 전체 페이지 리스트
		List<City> total = adminCityService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminCityService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminCityService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<City> city = adminCityService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminCityService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		// 검색 결과만 넘김
		model.addAttribute("city", city);
		
		return "admin/DB도시관리";
	}
			
// DB수정 페이지 이동(도시수정)
	@GetMapping("/DB도시수정/city/{cityNum}")
	public String dbupdate(@PathVariable("cityNum") int cityNum, Model model) {
		
		// 도시 넘버 Long타입으로 형변환
		Long cityNumL = Long.valueOf(cityNum);
		
		// 해당 도시번호의 도시 찾아오기
		List<City> city = adminCityService.findDataByNum(cityNumL);
		
		// 찾아온 도시를 DTO에 담아주기
		AdminCityDTO citydto = adminCityService.saveData(city);
		
		model.addAttribute("citydto", citydto);
		
		return "admin/DB도시수정";
	}
	
// DB 수정 form 날리기
	@PostMapping("/DB도시수정/city/{cityNum}")
	public String dbupdatePost(	@PathVariable("cityNum") int cityNum,
								@ModelAttribute("citydto") AdminCityDTO citydto,
								Model model) {
		
		adminCityService.updateData(citydto);
		
		// 관리자 페이지로 넘어갈 때 기본 페이지 1로 이동
		int pgNum = 1;
		
		// 전체 페이지 리스트
		List<City> total = adminCityService.findAllPageList();
		
		// 전체 페이지 개수
		int totalPage = adminCityService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminCityService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<City> city = adminCityService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminCityService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("city", city);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
				
		return "admin/DB도시관리";
	}
	
// 도시 삭제 이후 페이지 이동
	@GetMapping("/DB도시삭제/city/{cityNum}")
	public String deleteCountry(@PathVariable("cityNum") int cityNum) {
		adminCityService.deleteCity(cityNum);
		
		return "redirect:/admin";
	}
	
	
	
}



















