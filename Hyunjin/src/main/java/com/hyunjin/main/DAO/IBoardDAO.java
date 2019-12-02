package com.hyunjin.main.DAO;

import java.util.List;

import com.hyunjin.main.DTO.BoardDTO;
import com.hyunjin.main.util.Pagination;

public interface IBoardDAO {

	//게시글 출력 페이징 갯수
	public int BoardListCount();
	
	//게시글 출력 페이징
	public List<BoardDTO> BoardList(Pagination pagination);
	
	//메인 페이지에 보여질 최신 5개의 게시글 출력
	public List<BoardDTO> BoardListMain();
	
	//게시글 내용 조회
	public List<BoardDTO> BoardView(BoardDTO boardDTO);
	
	//게시글 작성
	public void BoardInsert(BoardDTO boardDTO);
	
	//게시글 수정
	public void BoardUpdate(BoardDTO boardDTO);
	
	//게시글 조회 시, 조회수 + 1
	public void BoardRefUpdate(BoardDTO boardDTO);
	
	//게시글 삭제
	public void BoardDelete(BoardDTO boardDTO);
}
