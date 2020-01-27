package com.hyunjin.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyunjin.main.DAO.IReplyDAO;
import com.hyunjin.main.DTO.ReplyDTO;

@Service
public class ReplyService implements IReplyService {
	
	@Autowired
	private IReplyDAO replyDAO;

	//게시글에 맞는 댓글 조회
	@Override
	public List<ReplyDTO> ReplyView(ReplyDTO replyDTO) {

		return replyDAO.ReplyView(replyDTO);
	}
	
	//댓글 수정 시, 해당 댓글 정보 조회
	@Override
	public List<ReplyDTO> ReplyOne(ReplyDTO replyDTO) {
		
		return replyDAO.ReplyOne(replyDTO);
	}
	
	//댓글 수정 시 필요한 비밀번호 유효성 검사
	@Override
	public String ReplyPassword(ReplyDTO replyDTO, String password) {
		
		String result = null;
		String getPassword = null;
		
		replyDTO = replyDAO.ReplyPassword(replyDTO);
		getPassword = replyDTO.getPassword();
		
		if(getPassword.equals(password))
			result = "success";
		else
			result = "fail";
		
		return result;
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
