package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.board.dto.BoardDTO;
import board.board.dto.BoardFileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList() throws Exception;
	void insertBoard(BoardDTO board) throws Exception;
	void updateHitCount(int boardIdx) throws Exception;
	BoardDTO selectBoardDetail(int boardIdx) throws Exception;
	void updateBoard(BoardDTO board) throws Exception;
	void deleteBoard(int boardIdx) throws Exception;
	void insertBoardFileList(List<BoardFileDTO> list) throws Exception;
	List<BoardFileDTO> selectBoardFileList(int boardIdx) throws Exception;
	BoardFileDTO selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx") int boardIdx);
}
