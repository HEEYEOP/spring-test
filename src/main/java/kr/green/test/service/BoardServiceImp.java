package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDAO;
import kr.green.test.dao.MemberDAO;
import kr.green.test.vo.BoardVO;
import kr.green.test.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	BoardDAO boardDao;

	@Override
	public ArrayList<BoardVO> getBoardList() {
	
		return boardDao.getBoardList();
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

	

}
