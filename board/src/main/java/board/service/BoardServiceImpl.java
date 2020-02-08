package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.dto.BoardDTO;
import board.mapper.BoardMapper;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDTO board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDTO selectBoardDetail(int boardIdx) throws Exception {
		
		boardMapper.updateHitCount(boardIdx);
		int i = 10 / 0;
		BoardDTO board = boardMapper.selectBoardDetail(boardIdx);
		return board;
	}

	@Override
	public void updateBoard(BoardDTO board) throws Exception {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
}
