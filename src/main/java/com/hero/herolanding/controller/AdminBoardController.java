package com.hero.herolanding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.Country;
import com.hero.herolanding.domain.Paper;
import com.hero.herolanding.dto.AdminBoardDTO;
import com.hero.herolanding.dto.BoardDTO;
import com.hero.herolanding.dto.AdminCountryDTO;
import com.hero.herolanding.service.AdminBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminBoardController {
	
	private final AdminBoardService adminBoardService;

// DB관리 메인 페이지
	@GetMapping("/게시글관리/{pgNum}")
	public String db(@PathVariable("pgNum") int pgNum,
					 Model model) {
		// 전체 페이지 리스트
		List<Board> total = adminBoardService.findAllPageList();
		
		// 전체 페이지 갯수
		int totalPage = adminBoardService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminBoardService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Board> board = adminBoardService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminBoardService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("board", board);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		
		return "admin/게시글관리";
	}

// DB 검색 form 날리기
	@PostMapping("/게시글관리/{pgNum}")
	public String dbSearch(@PathVariable("pgNum") int pgNum,
						   @RequestParam("select") String select,
						   @RequestParam("search") String search,
						   Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		pgNum = 1;
		
		// 검색한 결과의 전체 페이지 리스트
		List<Board> total = adminBoardService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminBoardService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminBoardService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 검색한 데이터 10개씩 뽑아오기
		List<Board> board = adminBoardService.findSearchData(select, search, nowPage);
		if(board.size() == 0) {
			board.add(new Board());
		}
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminBoardService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		// 검색 결과만 넘김
		model.addAttribute("board", board);
		
		return "admin/게시글관리";
	}
	
// DB 검색 form 날리고 페이지이동
	@PostMapping("/게시글관리/{pgNum}/paging")
	public String dbSearchPaging(@PathVariable("pgNum") int pgNum,
			@RequestParam("select") String select,
			@RequestParam("search") String search,
			Model model) {
		// get으로 페이지를 넘겼을 때, 1페이지가 아닌 경우 포스트로 넘어오는 순간 다시 1페이지로 돌려주기
		//pgNum = 1;
		
		// 검색한 결과의 전체 페이지 리스트
		List<Board> total = adminBoardService.findSearchPageList(select, search);
		
		// 전체 페이지 숫자
		int totalPage = adminBoardService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminBoardService.nextPrevPage(pgNum, totalPage);
		
		// 뷰 단에서 입력받은 페이지에 담길 국가 10개씩 뽑아오기
		List<Board> board = adminBoardService.findSearchData(select, search, nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminBoardService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String post = "post";
		
		model.addAttribute("post", post);
		model.addAttribute("select", select);
		model.addAttribute("search", search);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);
		// 검색 결과만 넘김
		model.addAttribute("board", board);
		
		return "admin/게시글관리";
	}

// DB수정 페이지 이동
	@GetMapping("/게시글수정/{boardNum}")
	public String dbupdate(@PathVariable("boardNum") int boardNum,
						   Model model) {
		
		// 국가 넘버 Long타입으로 형변환
		Long boardNumL = Long.valueOf(boardNum);
		
		// 해당 국가번호의 국가 찾아오기
		List<Board> board= adminBoardService.findDataByNum(boardNumL);

		// 찾아온 국가 DTO에 담아주기
		AdminBoardDTO boarddto = adminBoardService.saveData(board);
		
		model.addAttribute("boarddto", boarddto);
		
		return "admin/게시글수정";
	}
	
// DB 수정 form 날리기
	@PostMapping("/게시글수정/{boardNum}")
	public String dbupdatePost(@PathVariable("boardNum") int boardNum,
							   @ModelAttribute("boarddto") AdminBoardDTO boarddto,
							   Model model) {
		
		adminBoardService.updateData(boarddto);
		
		// 관리페이지로 넘어갈 때 기본 1페이지로 이동
		int pgNum = 1;
		
		// 전체 페이지 리스트
		List<Board> total = adminBoardService.findAllPageList();
		
		// 전체 페이지 갯수
		int totalPage = adminBoardService.findAllPageCnt(total);
		
		// 이전, 다음 페이지에 넣어줄 변수 만드는 메소드
		int nowPage = adminBoardService.nextPrevPage(pgNum, totalPage);
		
		// 페이지에 담길 전체 데이터 10개씩 뽑아오기
		List<Board> board = adminBoardService.findData(nowPage);
		
		// 전체 페이징 메소드
		List<Integer> totalPageCnt = adminBoardService.paging(nowPage, totalPage);
		
		// 페이징 부분을 결정하는 파라미터
		String get = "get";
		
		model.addAttribute("get", get);
		model.addAttribute("board", board);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("nowPage", nowPage);

		return "admin/게시글관리";
	}
	
// 국가 삭제 이후 페이지 이동
	@GetMapping("/게시글삭제/{boardNum}")
	public String deleteCountry(@PathVariable("boardNum") int boardNum) {
		
		adminBoardService.deleteCountry(boardNum);
		
		return "redirect:/admin";
	}

}
