package kr.green.test.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 
	@RequestMapping(value="/board/list", method= RequestMethod.GET) //value는 url의 경로를 넣어주는 것. 그래서 마지막에 자동으로 .jsp가 붙음
	public String BoardListGet(Model model) {
		
		ArrayList<BoardVO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
	
		return "board/list";	//이거는 URL가 아니고 파일 폴더 경로임. 그래서 앞에 자동으로 views/가 붙음
	}
	
	@RequestMapping(value="/board/display", method= RequestMethod.GET) 
	public String BoardDisplayGet(Model model, BoardVO board) {
		
		
		
		if(board != null) {
			BoardVO oneBoard = boardService.getBoard(board);
			System.out.println(oneBoard);
			
			if(oneBoard != null)
				model.addAttribute("oneBoard", oneBoard);
		}	
		return "board/display"; 
	}
	
	
	
	
	
	
	

}
