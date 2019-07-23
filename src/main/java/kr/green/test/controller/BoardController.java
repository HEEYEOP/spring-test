package kr.green.test.controller;

import java.awt.PageAttributes.OriginType;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.spring.pagination.Criteria;
import kr.green.spring.pagination.PageMaker;
import kr.green.test.dao.BoardDAO;
import kr.green.test.service.BoardService;
import kr.green.test.service.MemberService;
import kr.green.test.vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	BoardDAO boardDao;
	@Autowired
	BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	 
	@RequestMapping(value="/board/list", method= RequestMethod.GET) 
	public String BoardListGet(Model model, Criteria cri) {
		logger.info("게시판페이지 실행");
		int totalCount = boardService.countBoards();
		
		 PageMaker pageMaker = new PageMaker();
		 pageMaker.setCriteria(cri);
		 pageMaker.setDisplayPageNum(3);
		 pageMaker.setTotalCount(totalCount);
		 model.addAttribute("pageMaker", pageMaker);

		ArrayList<BoardVO> boardList = boardService.getBoardList(cri);
		model.addAttribute("boardList", boardList);
	
		return "board/list";	
	}
	
	
	
	
	
	
	@RequestMapping(value="/board/display", method= RequestMethod.GET) 
	public String BoardDisplayGet(Model model, BoardVO board) {
		logger.info("게시판 상세보기 실행");

	
		if(board != null) {
			BoardVO oneBoard = boardService.getBoard(board);
			System.out.println(oneBoard);
			
			if(oneBoard != null)
				model.addAttribute("oneBoard", oneBoard);
		}	
		return "board/display"; 
	}
	
	@RequestMapping(value="/board/enroll", method = RequestMethod.GET)
	public String BoardEnrollGet() {
		logger.info("T게시물 등록페이지 실행");
		return "board/enroll";
	}
	
	@RequestMapping(value="/board/enroll", method = RequestMethod.POST)
	public String BoardEnrollPost(BoardVO newBoard) {
		logger.info("T새 게시물 등록");
		System.out.println("T내가 작성한 새로운 게시물" + newBoard);
		
		boardService.insertBoard(newBoard);
		
		return "redirect:/board/list";
	}
	
	//게시물 수정----------------------------------------------
	
	@RequestMapping(value="/board/modify", method = RequestMethod.GET)
	public String BoardModifyGet(Model model, BoardVO originBoard, HttpServletRequest r) {
		logger.info("T게시물 수정페이지 실행");
		
		if(!boardService.isWriter(r, originBoard)) {
			model.addAttribute("num", originBoard);
			return "redirect:/board/list";
		}
		System.out.println(originBoard);
		model.addAttribute("origin", boardService.getBoard(originBoard));
		
		return "board/modify";
	}
	
	@RequestMapping(value="/board/modify", method = RequestMethod.POST)
	public String BoardModifyPost(BoardVO modifyBoard , HttpServletRequest r) {
		logger.info("T게시물 수정");
		System.out.println("수정된 게시물 정보 : "+modifyBoard);
		
		boardService.updateBoard(modifyBoard , r);
		
		return "redirect:/board/list";
	}
	
	//게시물 삭제--------------------------------------------------
	
	@RequestMapping(value="/board/delete", method = RequestMethod.GET)
	public String BoardDeleteGet(BoardVO deleteBoard, HttpServletRequest r) {
		logger.info("T게시물 삭제");
		if(boardService.isWriter(r, deleteBoard)) {
			boardService.deleteBoard(deleteBoard);
		}
		
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	

}
