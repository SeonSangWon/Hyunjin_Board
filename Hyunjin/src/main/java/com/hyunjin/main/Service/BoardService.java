package com.hyunjin.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyunjin.main.DAO.IBoardDAO;
import com.hyunjin.main.DTO.BoardDTO;
import com.hyunjin.main.util.Pagination;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO boardDAO;

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
		
		boardDAO.BoardInsert(boardDTO);
	}

	//게시글 수정
	@Override
	public void BoardUpdate(BoardDTO boardDTO) {
		
		boardDAO.BoardUpdate(boardDTO);
	}

	//게시글 조회 시, 조회수 + 1
	@Override
	public void BoardRefUpdate(BoardDTO boardDTO) {
		
		boardDAO.BoardRefUpdate(boardDTO);
	}

	//게시글 삭제
	@Override
	public void BoardDelete(BoardDTO boardDTO) {
		
		boardDAO.BoardDelete(boardDTO);
	}

}
