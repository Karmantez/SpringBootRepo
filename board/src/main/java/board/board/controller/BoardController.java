package board.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDTO;
import board.board.dto.BoardFileDTO;
import board.board.service.BoardService;

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
	public String insertBoard(BoardDTO board, MultipartHttpServletRequest multiHttpServletRequest) throws Exception {
		boardService.insertBoard(board, multiHttpServletRequest);
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
	
	@RequestMapping("/board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception {
		
		BoardFileDTO boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
		
		if(ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
