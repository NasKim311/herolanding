package com.hero.herolanding.controller;

import java.awt.print.Pageable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.Member;
import com.hero.herolanding.domain.Reply;
import com.hero.herolanding.domain.Report;
import com.hero.herolanding.dto.BoardDTO;
import com.hero.herolanding.dto.ReplyDTO;
import com.hero.herolanding.dto.ReportDTO;
import com.hero.herolanding.dto.SendDTO;
import com.hero.herolanding.dto.writeTypeDTO;
import com.hero.herolanding.repository.LoginRepository;
import com.hero.herolanding.service.BoardService;
import com.hero.herolanding.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final LoginRepository loginRepository;
//===================== 글 작성 ===============================
	@GetMapping("/board/write")
	public String write_form(Model model)
	{	
		model.addAttribute("boardDTO" , new BoardDTO());
		return "board/board_write";
	} // 글 작성 Form으로 이동 시켜주는 로직
	
	@PostMapping("/board/write")
	public String Board_write(BoardDTO dto , BindingResult bindingResult, HttpServletRequest request)
	{
		Board board = new Board();
		Member member = new Member(); // 추후에 로그인한 아이디가 있다면 아이디를 통해 멤버 정보를
									  // 가져와서 입력해주면 됨
		HttpSession session = request.getSession();
		member = (Member)session.getAttribute("loginMember");
		
		Member m = loginRepository.findById(member.getMemberId());
		
		System.out.println(dto.getPost_content());
		
		if(!StringUtils.hasText(dto.getPost_title()))
		{
			bindingResult.addError(new FieldError("dto", "post_title", dto.getPost_title(), false, null, null , "제목은 필수입니다."));
		} // 제목 검사 ( 값을 입력했는지 검사 )
		
		if(!StringUtils.hasText(dto.getPost_content()))
		{
			bindingResult.addError(new FieldError("dto", "post_content", dto.getPost_content(), false, null, null , "내용은 필수입니다."));
		} // 내용 검사 ( 값을 입력했는지 검사 )
		
		if(dto.getContinent() == null)
		{
			bindingResult.addError(new FieldError("dto", "continent", null, false, null, null , "여행지역을 입력해주세요"));
		} // 지역 선택했는지 검사
		
		if(dto.getWriteType() == null)
		{
			bindingResult.addError(new FieldError("dto", "writeType", dto.getWriteType(), false, null, null , "게시판을 선택해주세요"));
		} // 게시판 선택했는지 검사
		
		
		if(bindingResult.hasErrors()) return "board/board_write"; // 에러가 있는 경우 입력폼으로 다시 이동
		
		board.setBoardTitle(dto.getPost_title()); // 사용자가 입력한 제목
		board.setBoardContents(dto.getPost_content()); // 사용자가 입력한 내용
		board.setBoardCount(0L); // 조회수는 0
		board.setBoardType(dto.getWriteType()); // 사용자가 선택한 게시판 종류
		board.setContinent(dto.getContinent()); // 사용자가 선택한 대륙 종류
		board.setInsertDate(LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))); // 지금 시간을 포맷해서 넣어줌
		board.setReportCount(0L); // 신고 횟수는 0
		board.setUpdateDate(""); // 게시글 작성이기 때문에 update 날짜는 NULL
		board.setMember(m);
		boardService.inputBoardContents(board);
		
		return "redirect:/board/list/1";
	} // 작성한 글 DB에 저장하기 위한 로직
	
//===================== 리스트 불러오기 ===============================
	
	@GetMapping("/board/list/{id}")
	public String board_list(@PathVariable("id") Integer page ,Model model , HttpServletRequest request)
	{		
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member != null)
		{
			model.addAttribute("check", 1);
		}
		
		if(page < 1) // Previous를 눌러서 page값이 -일경우 첫번 쨰 페이지를 보여준다
		{
			page = 1;
		}
		if(page > (boardService.BoardCount().size() / 10) + 1 ) // Next를 눌러서 마지막 page값보다 초과한 경우 마지막 페이지를 보여준다.
		{
			page = (boardService.BoardCount().size() / 10) + 1;
		}

	
		model.addAttribute("current", page / 10);
			 // 현재 페이지를 보여주기위한 변수 etc: 14번쨰에 있다면 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18, 19 를보여줘야하고
			//   etc: 22번쨰에 있다면 21 , 22 , 23 ...
		
		if((page / 10) * 10 + 10 < (boardService.BoardCount().size() / 10) + 1) // 현제 보고있는 페이지보다 게시물이 100개 이상 있는 경우는 모두 보여줌
		{
			model.addAttribute("last", 9);
		}else
		{
			model.addAttribute("last", ((boardService.BoardCount().size() / 10) + 1) %10); // 현재 보고 있는 페이지의 게시물 100개보다 마지막 게시물이 적다면 게시물만큼 보여줌
		}
		writeTypeDTO dto = new writeTypeDTO();
		
		model.addAttribute("writeTypeDTO" , dto);
		model.addAttribute("list" , boardService.findAll(page)); 
		model.addAttribute("WholeCount", (boardService.BoardCount().size() / 10) + 1); // 전체 게시물 수
		model.addAttribute("now", page); // 현재 페이지
		return "board/board_list";
	} // 전체 글 리스트를 보여주기 위한 로직
	
	@GetMapping("/board/listByType/{key}/{id}")
	public String boardListByType(@PathVariable("key") String continent, @PathVariable("id") Integer page , Model model , HttpServletRequest request)
	{	
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member != null)
		{
			model.addAttribute("check", 1);
		}
		
		if(page < 1) 
		{
			page = 1;
		}
		List<Board> boards = boardService.findByType(continent);
		if(page > (boards.size() / 10) + 1 ) 
		{
			page = (boards.size() / 10) + 1;
		}
		
		if((page / 10) * 10 + 10 < (boards.size() / 10) + 1) // 현제 보고있는 페이지보다 게시물이 100개 이상 있는 경우는 모두 보여줌
		{
			model.addAttribute("last", 9);
		}else
		{
			model.addAttribute("last", ((boards.size() / 10) + 1) %10); // 현재 보고 있는 페이지의 게시물 100개보다 마지막 게시물이 적다면 게시물만큼 보여줌
		}
		
		List<Object[]> list = boardService.findAllByType(--page, continent);
		
		writeTypeDTO dto = new writeTypeDTO();
		
		model.addAttribute("writeTypeDTO" , dto);
		model.addAttribute("now", page);
		model.addAttribute("WholeCount", (boards.size() / 10) + 1);
		model.addAttribute("current", (int) page / 10);
		model.addAttribute("continents" , continent);
		model.addAttribute("boards", list);
		return "board/board_listByType";
	} // 대륙만 선택한 경우
	
	@GetMapping("/board/rangeSelect/{key}/{type}/{id}")
	public String ragneSelect(@PathVariable("key") String continent, @PathVariable("type") String boardType ,  @PathVariable("id") Integer page , Model model , HttpServletRequest request)
	{	
		
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member != null)
		{
			model.addAttribute("check", 1);
		}
		if(page < 1) 
		{
			page = 1;
		}
		List<Board> boards = boardService.rangeSelect(continent, boardType);
		if(page > (boards.size() / 10) + 1 ) 
		{
			page = (boards.size() / 10) + 1;
		}
		
		if((page / 10) * 10 + 10 < (boards.size() / 10) + 1) // 현제 보고있는 페이지보다 게시물이 100개 이상 있는 경우는 모두 보여줌
		{
			model.addAttribute("last", 9);
		}else
		{
			model.addAttribute("last", ((boards.size() / 10) + 1) %10); // 현재 보고 있는 페이지의 게시물 100개보다 마지막 게시물이 적다면 게시물만큼 보여줌
		}
		writeTypeDTO dto = new writeTypeDTO();
		
		model.addAttribute("writeTypeDTO" , dto);
		
		List<Object[]> list = boardService.findAllByRange(--page, continent, boardType);
		model.addAttribute("now", page);
		model.addAttribute("type", boardType);
		model.addAttribute("WholeCount", (boards.size() / 10) + 1);
		model.addAttribute("current", (int) page / 10);
		model.addAttribute("continents" , continent);
		model.addAttribute("boards", list);
		return "board/board_listByType";	
	}
	
	@GetMapping("/board/report/{id}")
	public String reportForm(@PathVariable("id")Long boardId , HttpServletRequest request, Model model)
	{	
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		ReportDTO dto = new ReportDTO();
		
		model.addAttribute("member", member);
		model.addAttribute("boardId" , boardId);
		model.addAttribute("ReportDTO", dto);
		return "board/board_report";
	}
	
	@PostMapping("/board/reporting")
	public String BoardReporting(ReportDTO dto , HttpServletRequest request)
	{
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		Member m = loginRepository.findById(member.getMemberId());
		Board b = boardService.findById(dto.getBoardId());
		
		Report report = new Report();
		report.setBoard(b);
		report.setMember(m);
		report.setReportReason(dto.getReportContents());
		
		boardService.inputReport(report);
		boardService.updateReportCount(dto.getBoardId());
		
		return "redirect:/";
	}
	
	@GetMapping("/board/{id}/view")
	public String board_detail(@PathVariable("id") Long boardId , Model model, HttpServletRequest request)
	{	
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		Member temp = new Member();
		
		temp.setMemberNum(-10L);
		Board board = boardService.findById(boardId);
		List<Object[]> comments = boardService.getComments(boardId);
		List<Object[]> replyComments = boardService.getReplyComment(boardId);
		if(member != null)
		{
			if(member.getMemberNum() == board.getMember().getMemberNum())
			{
				model.addAttribute("check" , 1);
			}
			temp.setMemberNum(member.getMemberNum());
		}
		
		model.addAttribute("memberNum", temp.getMemberNum());
		model.addAttribute("replyComments",replyComments);
		model.addAttribute("comments", comments);
		model.addAttribute("board", board);
		return "board/board_view";
	} // 아이디를 통해 게시물을 보기 위한 로직
	
	
//===================== 게시물 삭제 ===============================
	@GetMapping("/board/{id}/delete")
	public String boardDelte(@PathVariable("id")Long boardId)
	{	
		boardService.removeById(boardId);
		return "redirect:/board/list/1";
	} // 아이디를 통해 삭제하기 위한 로직
	
//===================== 게시물 수정 ===============================
	@GetMapping("/board/edit/{id}")
	public String editForm(Model model, @PathVariable("id")Long boardId )
	{	
		model.addAttribute("board", boardService.findById(boardId));
		model.addAttribute("boardDTO", new BoardDTO());
		return "board/board_edit";
	} // 수정 Form 으로 이동하기 위한 로직
	
	@PostMapping("/board/edit/{id}")
	public String edit(@PathVariable("id")Long boardId , BoardDTO boardDTO  , BindingResult bindingResult , Model model)
	{	
		
		if(!StringUtils.hasText(boardDTO.getPost_title()))
		{
			bindingResult.addError(new FieldError("dto", "post_title", boardDTO.getPost_title(), false, null, null , "제목은 필수입니다."));
		} // 제목 검사 ( 값을 입력했는지 검사 )
		
		if(!StringUtils.hasText(boardDTO.getPost_content()))
		{
			bindingResult.addError(new FieldError("dto", "post_content", boardDTO.getPost_content(), false, null, null , "내용은 필수입니다."));
		} // 내용 검사 ( 값을 입력했는지 검사 )
		
		if(boardDTO.getContinent() == null)
		{
			bindingResult.addError(new FieldError("dto", "continent", null, false, null, null , "여행지역을 입력해주세요"));
		} // 지역 선택했는지 검사
		
		if(boardDTO.getWriteType() == null)
		{
			bindingResult.addError(new FieldError("dto", "writeType", boardDTO.getWriteType(), false, null, null , "게시판을 선택해주세요"));
		} // 게시판 선택했는지 검사
		
		model.addAttribute("board", boardService.findById(boardId));
		if(bindingResult.hasErrors()) return "board/board_edit";
		boardDTO.setModifiedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		boardService.update(boardId,boardDTO);
		return "redirect:/board/list/1";
	} // 업데이트를 위한 로직
	
	@GetMapping("/board/search")
	public String search( Model model , writeTypeDTO dto)
	{	
		
		List<Object[]> list = boardService.Search(dto.getWriteType());
		model.addAttribute("last", 1);
		model.addAttribute("check", 0);
		model.addAttribute("page", 0);
		model.addAttribute("now", 0);
		model.addAttribute("type", 0);
		model.addAttribute("WholeCount", 0);
		model.addAttribute("current", 0);
		model.addAttribute("continents");
		model.addAttribute("list",list);
		return  "board/board_list";
	}
	
	@RequestMapping(value = "/board/comment", method = RequestMethod.POST)
	@ResponseBody
	public void writeComment(Model model, SendDTO dto , HttpServletRequest request )
	{	
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		
		Member m = new Member();
		m.setMemberNum(member.getMemberNum());
		
		dto.setWrite_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		Reply reply = new Reply();
		Board board = new Board();
		board.setBoardNum(dto.getBoardId()); // 어떤 게시판에 글을 썻는지 알기 위한 변수
		
		reply.setReplyContent(dto.getResult()); // 댓글 내용
		reply.setReplyDepthLevel(1); // 답글 구현 아직 안해서 depth는 무조건 1
		reply.setReplyInsertDate(LocalDateTime.now().
				format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))); // 댓글 작성 시간
		reply.setReplyUpdateDate(""); // 업데이트 날짜는 null
		reply.setBoard(board); // board 입력
		reply.setMember(m);
		
		boardService.inputComment(reply);
	} // 댓글 짜는 로직입니다 .AJAX 사용이기 때문에 따로 return 값없고 db에 댓글만 입력합니다.
	
	@RequestMapping(value = "/board/replyComment", method = RequestMethod.POST )
	@ResponseBody
	public void addReplyComment (SendDTO dto  , HttpServletRequest request )
	{	
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		
		Member m = new Member();
		m.setMemberNum(member.getMemberNum());
		
		dto.setWrite_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		Reply reply = new Reply();
		Board board = new Board();
		// Member member = new Member();
		board.setBoardNum(dto.getBoardId());
		
		reply.setReplyContent(dto.getResult());
		reply.setReplyDepthLevel(2); // 답글이기 때문에 depth 레벨은 2
		reply.setReplyInsertDate(LocalDateTime.now().
				format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		reply.setReplyUpdateDate("");
		reply.setBoard(board);
		reply.setMember(m);
		reply.setParentReplyNum(dto.getNum()); // 부모의 값도 넣어줘야함
		
		boardService.inputComment(reply);
	} // 답글 달기
	
		
	@RequestMapping(value = "/board/comment_update" , method = RequestMethod.POST)
	@ResponseBody
	public void updateComment(ReplyDTO dto)
	{
		boardService.comment_update(dto.getNum(), dto);
	} //댓글 업데이트

	
	@RequestMapping(value = "/board/comment_remove" ,method = RequestMethod.POST)
	@ResponseBody
	public void removeComment(ReplyDTO dto)
	{
		boardService.comment_remove(dto.getNum());
	} // 댓글 삭제
	
	

}
