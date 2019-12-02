package com.hyunjin.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyunjin.main.DAO.IReplyDAOd;
import com.hyunjin.main.DTO.ReplyDTO;

@Service
public class ReplyService implements IReplyService {
	
	@Autowired
	private IReplyDAOd replyDAO;

	//게시글에 맞는 댓글 조회
	@Override
	public List<ReplyDTO> ReplyView(ReplyDTO replyDTO) {

		return replyDAO.ReplyView(replyDTO);
	}

	//댓글 등록
	@Override
	public void ReplyInsert(ReplyDTO replyDTO) {

		replyDAO.ReplyInsert(replyDTO);
	}

	//댓글 수정(비밀번호가 일치하는 사용자만 변경 가능)
	@Override
	public void ReplyUpdate(ReplyDTO replyDTO) {

		replyDAO.ReplyUpdate(replyDTO);
	}

	//댓글 삭제(비밀번호가 일치하는 사용자만 삭제 가능)
	@Override
	public void ReplyDelete(ReplyDTO replyDTO) {

		replyDAO.ReplyDelete(replyDTO);
	}

}
