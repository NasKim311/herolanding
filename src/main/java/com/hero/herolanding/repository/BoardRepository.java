package com.hero.herolanding.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hero.herolanding.domain.Board;
import com.hero.herolanding.domain.BoardType;
import com.hero.herolanding.domain.Continent;
import com.hero.herolanding.domain.Reply;
import com.hero.herolanding.domain.Report;
import com.hero.herolanding.dto.BoardDTO;
import com.hero.herolanding.dto.ReplyDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

	private final EntityManager em;

	// 리스트 값 넣기
	public void inputBoardContents(Board board) {
		em.persist(board);
	}

	public void inputReport(Report report) {
		em.persist(report);
	}

	public void updateReportCount(long boardId) {
		Board b = findById(boardId);
		long temp = b.getReportCount();
		b.setReportCount(temp + 1);
	}

	public List<Board> BoardCount() {
		return em.createQuery("select b from Board b", Board.class).getResultList();
	}

	// 리스트 전체 출력
	public List<Object[]> FindAll(int page) {
		if (page == 1)
			page = 0;
		else {
			page--;
			page = page * 10;
		}
		return em.createQuery(
				"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum order by b.boardNum desc")
				.setFirstResult(page).setMaxResults(10).getResultList();
	}

	public List<Object[]> Search(String searchText , String text) {
		String temp = "%" + searchText + "%";
		String temp2 = "";
		
		if(text.equals("title"))
		{
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.boardTitle LIKE :search order by b.boardNum desc")
					.setParameter("search", temp).getResultList();
		}
		else if(text.equals("content"))
		{
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.boardContents LIKE :search order by b.boardNum desc")
					.setParameter("search", temp).getResultList();
		}
		else if(text.equals("writer"))
		{
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and m.memberNickName LIKE :search order by b.boardNum desc")
					.setParameter("search", temp).getResultList();
		}
		return null;		
	}

	public List<Object[]> findAllByType(int page, String boardType) {

		page = page * 10;
		switch (boardType) {
		case "ASIA":
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :type order by b.boardNum desc ")
					.setParameter("type", Continent.ASIA).setFirstResult(page).setMaxResults(10).getResultList();
		case "EUROPE":
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :type order by b.boardNum desc ")
					.setParameter("type", Continent.EUROPE).setFirstResult(page).setMaxResults(10).getResultList();
		case "NorthAmerica":
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :type order by b.boardNum desc ")
					.setParameter("type", Continent.NorthAmerica).setFirstResult(page).setMaxResults(10)
					.getResultList();
		case "SouthAmerica":
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :type order by b.boardNum desc ")
					.setParameter("type", Continent.SouthAmerica).setFirstResult(page).setMaxResults(10)
					.getResultList();
		case "AFRICA":
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :type order by b.boardNum desc ")
					.setParameter("type", Continent.AFRICA).setFirstResult(page).setMaxResults(10).getResultList();
		case "OCEANIA":
			return em.createQuery(
					"select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :type order by b.boardNum desc ")
					.setParameter("type", Continent.OCEANIA).setFirstResult(page).setMaxResults(10).getResultList();
		}
		return null;
	}

	public List<Object[]> findAllByRange(int page, String continent, String boardType) {
		page = page * 10;
		String sql = "select b.boardNum,b.boardTitle, m.memberNickName, b.insertDate,b.boardCount from Board b INNER JOIN Member m on b.member = m.memberNum and b.continent = :continent AND b.boardType = :type order by b.boardNum desc ";
		switch (continent) {
		case "ASIA":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(sql).setParameter("continent", Continent.ASIA)
						.setParameter("type", BoardType.REVIEW).setFirstResult(page).setMaxResults(10).getResultList();
			case "FREE":
				return em.createQuery(sql).setParameter("continent", Continent.ASIA)
						.setParameter("type", BoardType.FREE).setFirstResult(page).setMaxResults(10).getResultList();
			case "FOOD":
				return em.createQuery(sql).setParameter("continent", Continent.ASIA)
						.setParameter("type", BoardType.FOOD).setFirstResult(page).setMaxResults(10).getResultList();
			}
		case "EUROPE":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(sql).setParameter("continent", Continent.EUROPE)
						.setParameter("type", BoardType.REVIEW).setFirstResult(page).setMaxResults(10).getResultList();
			case "FREE":
				return em.createQuery(sql).setParameter("continent", Continent.EUROPE)
						.setParameter("type", BoardType.FREE).setFirstResult(page).setMaxResults(10).getResultList();
			case "FOOD":
				return em.createQuery(sql).setParameter("continent", Continent.EUROPE)
						.setParameter("type", BoardType.FOOD).setFirstResult(page).setMaxResults(10).getResultList();
			}
		case "NorthAmerica":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(sql).setParameter("continent", Continent.NorthAmerica)
						.setParameter("type", BoardType.REVIEW).setFirstResult(page).setMaxResults(10).getResultList();
			case "FREE":
				return em.createQuery(sql).setParameter("continent", Continent.NorthAmerica)
						.setParameter("type", BoardType.FREE).setFirstResult(page).setMaxResults(10).getResultList();
			case "FOOD":
				return em.createQuery(sql).setParameter("continent", Continent.NorthAmerica)
						.setParameter("type", BoardType.FOOD).setFirstResult(page).setMaxResults(10).getResultList();
			}
		case "SouthAmerica":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(sql).setParameter("continent", Continent.SouthAmerica)
						.setParameter("type", BoardType.REVIEW).setFirstResult(page).setMaxResults(10).getResultList();
			case "FREE":
				return em.createQuery(sql).setParameter("continent", Continent.SouthAmerica)
						.setParameter("type", BoardType.FREE).setFirstResult(page).setMaxResults(10).getResultList();
			case "FOOD":
				return em.createQuery(sql).setParameter("continent", Continent.SouthAmerica)
						.setParameter("type", BoardType.FOOD).setFirstResult(page).setMaxResults(10).getResultList();
			}
		case "AFRICA":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(sql).setParameter("continent", Continent.AFRICA)
						.setParameter("type", BoardType.REVIEW).setFirstResult(page).setMaxResults(10).getResultList();
			case "FREE":
				return em.createQuery(sql).setParameter("continent", Continent.AFRICA)
						.setParameter("type", BoardType.FREE).setFirstResult(page).setMaxResults(10).getResultList();
			case "FOOD":
				return em.createQuery(sql).setParameter("continent", Continent.AFRICA)
						.setParameter("type", BoardType.FOOD).setFirstResult(page).setMaxResults(10).getResultList();
			}
		case "OCEANIA":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(sql).setParameter("continent", Continent.OCEANIA)
						.setParameter("type", BoardType.REVIEW).setFirstResult(page).setMaxResults(10).getResultList();
			case "FREE":
				return em.createQuery(sql).setParameter("continent", Continent.OCEANIA)
						.setParameter("type", BoardType.FREE).setFirstResult(page).setMaxResults(10).getResultList();
			case "FOOD":
				return em.createQuery(sql).setParameter("continent", Continent.OCEANIA)
						.setParameter("type", BoardType.FOOD).setFirstResult(page).setMaxResults(10).getResultList();
			}
		}
		return null;
	}

	// 타입 별로 리스트 가져오기
	public List<Board> findByType(String boardType) {
		switch (boardType) {
		case "ASIA":
			return em.createQuery("select b from Board b where b.continent = :type order by b.boardNum desc ",
					Board.class).setParameter("type", Continent.ASIA).getResultList();
		case "EUROPE":
			return em.createQuery("select b from Board b where b.continent = :type order by b.boardNum desc",
					Board.class).setParameter("type", Continent.EUROPE).getResultList();
		case "NorthAmerica":
			return em.createQuery("select b from Board b where b.continent = :type order by b.boardNum desc",
					Board.class).setParameter("type", Continent.NorthAmerica).getResultList();
		case "SouthAmerica":
			return em.createQuery("select b from Board b where b.continent = :type order by b.boardNum desc",
					Board.class).setParameter("type", Continent.SouthAmerica).getResultList();
		case "AFRICA":
			return em.createQuery("select b from Board b where b.continent = :type order by b.boardNum desc",
					Board.class).setParameter("type", Continent.AFRICA).getResultList();
		case "OCEANIA":
			return em.createQuery("select b from Board b where b.continent = :type order by b.boardNum desc",
					Board.class).setParameter("type", Continent.OCEANIA).getResultList();
		}
		return null;
	}

	// 대륙별 + 게시판 교차 리스트 가져오기
	public List<Board> rangeSelect(String continent, String boardType) {
		switch (continent) {
		case "ASIA":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(
						"select b from Board b where b.continent = :continent AND b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.ASIA).setParameter("type", BoardType.REVIEW)
						.getResultList();
			case "FREE":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.ASIA).setParameter("type", BoardType.FREE)
						.getResultList();
			case "FOOD":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.ASIA).setParameter("type", BoardType.FOOD)
						.getResultList();
			}
		case "EUROPE":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(
						"select b from Board b where b.continent = :continent AND b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.EUROPE).setParameter("type", BoardType.REVIEW)
						.getResultList();
			case "FREE":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.EUROPE).setParameter("type", BoardType.FREE)
						.getResultList();
			case "FOOD":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.EUROPE).setParameter("type", BoardType.FOOD)
						.getResultList();
			}
		case "NorthAmerica":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(
						"select b from Board b where b.continent = :continent AND b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.NorthAmerica)
						.setParameter("type", BoardType.REVIEW).getResultList();
			case "FREE":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.NorthAmerica)
						.setParameter("type", BoardType.FREE).getResultList();
			case "FOOD":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.NorthAmerica)
						.setParameter("type", BoardType.FOOD).getResultList();
			}
		case "SouthAmerica":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(
						"select b from Board b where b.continent = :continent AND b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.SouthAmerica)
						.setParameter("type", BoardType.REVIEW).getResultList();
			case "FREE":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.SouthAmerica)
						.setParameter("type", BoardType.FREE).getResultList();
			case "FOOD":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.SouthAmerica)
						.setParameter("type", BoardType.FOOD).getResultList();
			}
		case "AFRICA":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(
						"select b from Board b where b.continent = :continent AND b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.AFRICA).setParameter("type", BoardType.REVIEW)
						.getResultList();
			case "FREE":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.AFRICA).setParameter("type", BoardType.FREE)
						.getResultList();
			case "FOOD":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.AFRICA).setParameter("type", BoardType.FOOD)
						.getResultList();
			}
		case "OCEANIA":
			switch (boardType) {
			case "REVIEW":
				return em.createQuery(
						"select b from Board b where b.continent = :continent AND b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.OCEANIA).setParameter("type", BoardType.REVIEW)
						.getResultList();
			case "FREE":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.OCEANIA).setParameter("type", BoardType.FREE)
						.getResultList();
			case "FOOD":
				return em.createQuery(
						"select b from Board b where b.continent = :continent and b.boardType = :type order by b.boardNum desc ",
						Board.class).setParameter("continent", Continent.OCEANIA).setParameter("type", BoardType.FOOD)
						.getResultList();
			}
		}
		return null;
	}

	// 리스트 중 아디를 통해 찾아오기
	public Board findById(Long boardId) {
		return em.createQuery("select b from Board b where b.boardNum = :id", Board.class).setParameter("id", boardId)
				.getSingleResult();
	}

	// 댓글 번호로 댓글 가져오기
	public Reply findCommentById(Long replyId) {
		return em.createQuery("select r from Reply r where r.replyNum = :id", Reply.class).setParameter("id", replyId)
				.getSingleResult();
	}

//	// 삭제
	public void removeById(Long boardId) {
		Board findBoard = findById(boardId);
		em.remove(findBoard);
	}

//	
	// 댓글 불러오기
	public List<Object[]> getComments(Long boardId) {
		return em.createQuery(
				"select r.replyNum,r.ParentReplyNum,r.replyInsertDate,r.replyContent,r.member,r.board, m.memberNickName from Reply r , Member m INNER JOIN Board b on r.board = b.boardNum and r.member = m.memberNum where b.boardNum = :board_num and r.replyDepthLevel = 1 order by r.replyNum desc")
				.setParameter("board_num", boardId).getResultList();
	}

	// 답글 불러오기
	public List<Object[]> getReplyComment(Long boardId) {
		return em.createQuery(
				"select r.replyNum,r.ParentReplyNum,r.replyInsertDate,r.replyContent,r.member,r.board, m.memberNickName from Reply r , Member m INNER JOIN Board b on r.board = b.boardNum and r.member = m.memberNum where b.boardNum = :board_num and r.replyDepthLevel = 2 order by r.replyNum desc")
				.setParameter("board_num", boardId).getResultList();
	}

//	// 댓글 입력
	public void inputComment(Reply reply) {
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
	public void update(Long boardId, BoardDTO updateBoard) {
		Board findBoard = findById(boardId);

		findBoard.setBoardContents(updateBoard.getPost_content());
		findBoard.setBoardTitle(updateBoard.getPost_title());
		findBoard.setContinent(updateBoard.getContinent());
		findBoard.setBoardType(updateBoard.getWriteType());
		findBoard.setInsertDate(updateBoard.getModifiedDate());
	} // 게시판 글 업데이트

	public void comment_update(Long ReplyNum, ReplyDTO dto) {
		Reply findReply = findCommentById(ReplyNum);
		findReply.setReplyContent(dto.getChangeData());
	} // 게시판 댓글 업데이트

	public void comment_remove(Long ReplyNum) {
		Reply findReply = findCommentById(ReplyNum);
		em.remove(findReply);
	} // 게시판 댓글 삭제

}
