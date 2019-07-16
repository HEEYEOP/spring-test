package kr.green.test.dao;

import java.util.ArrayList;

import kr.green.test.vo.BoardVO;

public interface BoardDAO {

	ArrayList<BoardVO> getBoardList();
	
	BoardVO getBoard(BoardVO board);
	
	void viewsCount(BoardVO board);

	void insertBoard(BoardVO newBoard);

	void updateBoard(BoardVO modifyBoard);

}
