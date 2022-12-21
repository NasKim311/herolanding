package com.hero.herolanding.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.Reply;
import com.hero.herolanding.dto.BoardDTO;
import com.hero.herolanding.dto.ReplyDTO;
import com.hero.herolanding.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public void inputBoardContents(Board board)
	{
		boardRepository.inputBoardContents(board);
	}
	
	@Transactional
	public List<Object[]> findAll(int page)
	{
		return boardRepository.FindAll(page);
	}
	
	@Transactional
	public List<Board> BoardCount()
	{
		return boardRepository.BoardCount();
	}
	
	@Transactional
	public Board findById(Long boardId)
	{	
		Board board = boardRepository.findById(boardId);
		board.updateViewCount(board.getBoardCount());
		return board;
	}

	@Transactional
	public void removeById(Long boardId)
	{
		boardRepository.removeById(boardId);
	}
//	
	@Transactional
	public List<Reply> getComments(Long boardId)
	{
		return boardRepository.getComments(boardId);
	}
	
	@Transactional
	public void inputComment(Reply reply)
	{
		boardRepository.inputComment(reply);
	}
//	
//	@Transactional
//	public List<Board> findByType(String type)
//	{
//		return boardRepository.findByType(type);
//	}
//	
	@Transactional
	public void update(Long boardId , BoardDTO updateBoard)
	{
		boardRepository.update(boardId, updateBoard);
	}
	
	@Transactional
	public Reply findCommentById(Long replyId)
	{
		return boardRepository.findCommentById(replyId);
	}
	
	@Transactional
	public void comment_remove(Long replyId)
	{
		boardRepository.comment_remove(replyId);
	}
	
	@Transactional
	public void comment_update(Long replyId , ReplyDTO dto)
	{
		boardRepository.comment_update(replyId, dto);
	}
	
	@Transactional
	public List<Board> findByType(String boardType)
	{
		return boardRepository.findByType(boardType);
	}
	
	@Transactional
	public List<Board> rangeSelect(String continent , String boardType)
	{
		return boardRepository.rangeSelect(continent, boardType);
	}
	@Transactional
	public List<Object[]> findAllByType(int page , String boardType)
	{
		return boardRepository.findAllByType(page, boardType);
	}
	
	@Transactional
	public List<Object[]> findAllByRange(int page , String continent , String boardType)
	{
		return boardRepository.findAllByRange(page, continent, boardType);
	}
	
	@Transactional
	public List<Reply> getReplyComment(Long boardId)
	{
		return boardRepository.getReplyComment(boardId);
	}
}
