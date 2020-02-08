package board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDTO;
import board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		
		log.debug("openBoardList");
		
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDTO> list = boardService.selectBoardList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
		
		log.debug("openBoardDetail");
		
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		System.out.println(mv);
		
		BoardDTO board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDTO board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDTO board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
}
