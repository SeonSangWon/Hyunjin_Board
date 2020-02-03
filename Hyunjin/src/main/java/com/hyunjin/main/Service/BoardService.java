package com.hyunjin.main.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyunjin.main.Controller.BoardController;
import com.hyunjin.main.DAO.IBoardDAO;
import com.hyunjin.main.DTO.BoardDTO;
import com.hyunjin.main.util.Pagination;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO boardDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	//게시글 출력 페이징 갯수
	@Override
	public int BoardListCount() {

		return boardDAO.BoardListCount();
	}

	//게시글 출력 페이징
	@Override
	public List<BoardDTO> BoardList(Pagination pagination) {

		return boardDAO.BoardList(pagination);
	}

	//메인 페이지에 보여질 최신 5개의 게시글 출력
	@Override
	public List<BoardDTO> BoardListMain() {

		return boardDAO.BoardListMain();
	}

	//게시글 내용 조회
	@Override
	public List<BoardDTO> BoardView(BoardDTO boardDTO) {

		return boardDAO.BoardView(boardDTO);
	}

	//게시글 작성
	@Override
	public void BoardInsert(BoardDTO boardDTO) {
		
		//줄 바꿈
		boardDTO.setComment(boardDTO.getComment().replace("\r\n","<br>"));
		
		//return
		boardDAO.BoardInsert(boardDTO);
	}

	//게시글 수정
	@Override
	public void BoardUpdate(BoardDTO boardDTO) {
		
		//줄 바꿈
		boardDTO.setComment(boardDTO.getComment().replace("\r\n","<br>"));
		
		//return
		boardDAO.BoardUpdate(boardDTO);
	}

	//게시글 조회 시, 조회수 + 1
	@Override
	public void BoardRefUpdate(BoardDTO boardDTO) {
		
		boardDAO.BoardRefUpdate(boardDTO);
		logger.info("▶▶▶BoardRefUpdate◀◀◀");
	}

	//게시글 삭제
	@Override
	public void BoardDelete(BoardDTO boardDTO) {
		
		boardDAO.BoardDelete(boardDTO);
	}

}
