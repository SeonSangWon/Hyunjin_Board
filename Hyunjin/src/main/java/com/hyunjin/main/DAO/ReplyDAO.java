package com.hyunjin.main.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyunjin.main.DTO.ReplyDTO;

@Repository
public class ReplyDAO implements IReplyDAOd {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.hyunjin.mapper.replyMapper";
	
	//게시글에 맞는 댓글 조회
	@Override
	public List<ReplyDTO> ReplyView(ReplyDTO replyDTO) {

		return sqlSession.selectList(Namespace+".ReplyView", replyDTO);
	}
	
	//댓글 수정 시 필요한 비밀번호 유효성 검사
	@Override
	public ReplyDTO replyUpdatePassword(ReplyDTO replyDTO) {
		
		return sqlSession.selectOne(Namespace+".replyUpdatePassword", replyDTO);
	}

	//댓글 등록
	@Override
	public void ReplyInsert(ReplyDTO replyDTO) {

		sqlSession.insert(Namespace+".ReplyInsert", replyDTO);
	}

	//댓글 수정(비밀번호가 일치하는 사용자만 변경 가능)
	@Override
	public void ReplyUpdate(ReplyDTO replyDTO) {

		sqlSession.update(Namespace+".ReplyUpdate", replyDTO);
	}

	//댓글 삭제(비밀번호가 일치하는 사용자만 삭제 가능)
	@Override
	public void ReplyDelete(ReplyDTO replyDTO) {

		sqlSession.delete(Namespace+".ReplyDelete", replyDTO);
	}

}
