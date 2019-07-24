package kr.green.test.dao;

import java.util.ArrayList;

import kr.green.spring.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardDAO {

	ArrayList<BoardVO> getBoardList(Criteria cri);
	
	BoardVO getBoard(BoardVO board);
	
	void viewsCount(BoardVO board);

	void insertBoard(BoardVO newBoard);

	void updateBoard(BoardVO modifyBoard);

	void updateValid(BoardVO deleteBoard);

	int countBoards(Criteria cri);


}
