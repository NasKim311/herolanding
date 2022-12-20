package com.hero.herolanding.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.BoardType;
import com.hero.herolanding.domain.Reply;
import com.hero.herolanding.dto.BoardDTO;
import com.hero.herolanding.dto.ReplyDTO;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class BoardRepository {
	
	private final EntityManager em;
	
	// 리스트 값 넣기
	public void inputBoardContents(Board board)
	{
		em.persist(board);
	}
	
	public List<Board> BoardCount()
	{
		return em.createQuery("select b from Board b", Board.class).getResultList();
	}
	
	// 리스트 전체 출력
	public List<Board> FindAll(int page)
	{	
		if(page == 1) page = 0;
		else {
			page--;
			page = page * 10;
		}
		return em.createQuery("select b from Board b order by b.boardNum desc" , Board.class).setFirstResult(page).setMaxResults(10).getResultList();
	}
	// 타입 별로 리스트 가져오기
	public List<Board> findByType(String boardType)
	{	
		switch(boardType)
		{
			case "TIP" :
				return em.createQuery("select b from Board b where b.boardType = :type", Board.class).setParameter("type", BoardType.REVIEW).getResultList();
			case "FREE" :
				return em.createQuery("select b from Board b where b.boardType = :type", Board.class).setParameter("type", BoardType.FREE).getResultList();
		}
		return null;
		
	}

	// 리스트 중 아디를 통해 찾아오기
	public Board findById(Long boardId)
	{
		return em.createQuery("select b from Board b where b.boardNum = :id", Board.class).setParameter("id", boardId).getSingleResult();
	}
	
	//댓글 번호로 댓글 가져오기
	public Reply findCommentById(Long replyId)
	{
		return em.createQuery("select r from Reply r where r.replyNum = :id" , Reply.class).setParameter("id", replyId).getSingleResult();
	}
	
//	// 삭제
	public void removeById(Long boardId)
	{
		Board findBoard = findById(boardId);
		em.remove(findBoard);
	}
//	
	// 댓글 불러오기
	public List<Reply> getComments(Long boardId)
	{	
		return em.createQuery("select r from Reply r INNER JOIN Board b on r.board = b.boardNum where b.boardNum = :board_num order by r.replyNum desc", Reply.class)
				.setParameter("board_num", boardId)
				.getResultList();
	}
//	// 댓글 입력
	public void inputComment(Reply reply)
	{
		em.persist(reply);
	}
//	
//	// 게시판 별로 갖고오기
//	public List<Board> findByType(String type)
//	{
//		return em.createQuery("select b from Board b where b.write_type = :type" , Board.class)
//				.setParameter("type", type).getResultList();
//	}
//	
//	// 값 업데이트
	public void update(Long boardId , BoardDTO updateBoard)
	{
		Board findBoard = findById(boardId);
		
		findBoard.setBoardContents(updateBoard.getPost_content());
		findBoard.setBoardTitle(updateBoard.getPost_title());
		findBoard.setContinent(updateBoard.getContinent());
		findBoard.setBoardType(updateBoard.getWriteType());
		findBoard.setInsertDate(updateBoard.getModifiedDate());
	} // 게시판 글 업데이트
	
	public void comment_update(Long ReplyNum , ReplyDTO dto)
	{
		Reply findReply = findCommentById(ReplyNum);
		findReply.setReplyContent(dto.getChangeData());
	} // 게시판 댓글 업데이트
	
	public void comment_remove(Long ReplyNum)
	{
		Reply findReply = findCommentById(ReplyNum);
		em.remove(findReply);
	} // 게시판 댓글 삭제
	
	

}
