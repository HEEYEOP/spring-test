package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.spring.pagination.Criteria;
import kr.green.test.dao.BoardDAO;
import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardVO> getBoardList(Criteria cri) {
	
		return boardDao.getBoardList(cri);
	}

	@Override
	public BoardVO getBoard(BoardVO board) {
		boardDao.viewsCount(board);
		return boardDao.getBoard(board);
	}

	@Override
	public void insertBoard(BoardVO newBoard) {
		if(newBoard == null) return ;
		
		boardDao.insertBoard(newBoard);
		
		
		
	}

	@Override
	public void updateBoard(BoardVO modifyBoard, HttpServletRequest r) {
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");
		if(user.getId().equals(modifyBoard.getWriter())) {
			boardDao.updateBoard(modifyBoard);
		}
		
		
	}

	@Override
	public void deleteBoard(BoardVO deleteBoard) {
		boardDao.updateValid(deleteBoard);
	}

	@Override
	public boolean isWriter(HttpServletRequest r, BoardVO originBoard) {
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");
		BoardVO board = boardDao.getBoard(originBoard);
		if(user != null && board != null && user.getId().equals(board.getWriter())) {
			return true;
		}
		return false;
	}

	@Override
	public int countBoards(Criteria cri) {
		
		return boardDao.countBoards(cri);
	}
	
	

	

}
