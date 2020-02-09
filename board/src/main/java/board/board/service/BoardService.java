package board.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDTO;
import board.board.dto.BoardFileDTO;

public interface BoardService {
	List<BoardDTO> selectBoardList() throws Exception;
	void insertBoard(BoardDTO board, MultipartHttpServletRequest multiHttpServletRequest) throws Exception;
	BoardDTO selectBoardDetail(int boardIdx) throws Exception;
	void updateBoard(BoardDTO board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
	BoardFileDTO selectBoardFileInformation(int idx, int boardIdx) throws Exception;
}
