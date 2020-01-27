package com.hyunjin.main.DAO;

import java.util.List;

import com.hyunjin.main.DTO.ReplyDTO;

public interface IReplyDAO {

	//게시글에 맞는 댓글 조회
	public List<ReplyDTO> ReplyView(ReplyDTO replyDTO);

	//댓글 수정 시, 해당 댓글 정보 조회
	public List<ReplyDTO> ReplyOne(ReplyDTO replyDTO);
	
	//댓글 수정 시 필요한 비밀번호 유효성 검사
	public ReplyDTO ReplyPassword(ReplyDTO replyDTO);
	
	//댓글 등록
	public void ReplyInsert(ReplyDTO replyDTO);
	
	//댓글 수정(비밀번호가 일치하는 사용자만 변경 가능)
	public void ReplyUpdate(ReplyDTO replyDTO);
	
	//댓글 삭제(비밀번호가 일치하는 사용자만 삭제 가능)
	public void ReplyDelete(ReplyDTO replyDTO);
}
