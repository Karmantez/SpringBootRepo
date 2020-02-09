package board.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDTO;
import board.board.dto.BoardFileDTO;
import board.board.mapper.BoardMapper;
import board.common.FileUtils;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<BoardDTO> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDTO board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		 boardMapper.insertBoard(board);
		 List<BoardFileDTO> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		 
		 if(!CollectionUtils.isEmpty(list))
			 boardMapper.insertBoardFileList(list);
//		if(ObjectUtils.isEmpty(multiHttpServletRequest) == false) {
//			
//			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//			String name;
//			
//			while(iterator.hasNext()) {
//				
//				name = iterator.next();
//				
//				log.debug("file tag name: " + name);
//				
//				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
//				
//				for(MultipartFile multipartFile : list) {
//					
//					log.debug("start file information");
//					log.debug("file name: " + multipartFile.getOriginalFilename());
//					log.debug("file size: " + multipartFile.getSize());
//					log.debug("file content type: " + multipartFile.getContentType());
//					log.debug("end file information\n");
//				}
//			}
//		}
	}

	@Override
	public BoardDTO selectBoardDetail(int boardIdx) throws Exception {

		BoardDTO board = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDTO> fileList = boardMapper.selectBoardFileList(boardIdx);
		boardMapper.updateHitCount(boardIdx);
		
		board.setFileList(fileList);
		
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

	@Override
	public BoardFileDTO selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
}
