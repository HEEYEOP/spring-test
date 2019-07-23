package kr.green.test.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.spring.pagination.Criteria;
import kr.green.test.vo.BoardVO;

public interface BoardService {

	ArrayList<BoardVO> getBoardList(Criteria cri);

	BoardVO getBoard(BoardVO board);

	void insertBoard(BoardVO newBoard);

	void updateBoard(BoardVO modifyBoard, HttpServletRequest r);

	void deleteBoard(BoardVO deleteBoard);

	boolean isWriter(HttpServletRequest r, BoardVO originBoard);

	int countBoards();


	

}
