package com.hyunjin.main.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyunjin.main.DTO.BoardDTO;
import com.hyunjin.main.util.Pagination;

@Repository
public class BoardDAO implements IBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.hyunjin.mapper.boardMapper";

	//게시글 출력 페이징 갯수
	@Override
	public int BoardListCount() {
		
		return sqlSession.selectOne(Namespace+".BoardListCount");
	}

	//게시글 출력 페이징
	@Override
	public List<BoardDTO> BoardList(Pagination pagination) {
		
		return sqlSession.selectList(Namespace+".BoardList", pagination);
	}

	//메인 페이지에 보여질 최신 5개의 게시글 출력
	@Override
	public List<BoardDTO> BoardListMain() {

		return sqlSession.selectList(Namespace+".BoardListMain");
	}

	//게시글 내용 조회
	@Override
	public List<BoardDTO> BoardView(BoardDTO boardDTO) {

		return sqlSession.selectList(Namespace+".BoardView", boardDTO);
	}

	//게시글 작성
	@Override
	public void BoardInsert(BoardDTO boardDTO) {
		
		sqlSession.insert(Namespace+".BoardInsert", boardDTO);
	}

	//게시글 수정
	@Override
	public void BoardUpdate(BoardDTO boardDTO) {
		
		sqlSession.update(Namespace+".BoardUpdate", boardDTO);
	}

	//게시글 조회 시, 조회수 + 1
	@Override
	public void BoardRefUpdate(BoardDTO boardDTO) {
		
		sqlSession.update(Namespace+".BoardRefUpdate", boardDTO);
	}

	//게시글 삭제
	@Override
	public void BoardDelete(BoardDTO boardDTO) {
		
		sqlSession.delete(Namespace+".BoardDelete", boardDTO);
	}

}
