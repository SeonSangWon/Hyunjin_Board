package com.hyunjin.main.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyunjin.main.DTO.ReplyDTO;
import com.hyunjin.main.Service.IReplyService;

@Controller
public class ReplyController {

	@Autowired
	private IReplyService replyService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
		
	//댓글 등록
	@RequestMapping(value = "replyInsert", method = RequestMethod.POST)
	public String insert(ReplyDTO replyDTO, Model model, HttpServletRequest request) {
		
		try {
			
			replyService.ReplyInsert(replyDTO);
			logger.info("=====Reply Data Insert=====");
			
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", replyDTO.getBid());
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("ReplyInsert() Error!!!");
			
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", replyDTO.getBid());
		}
		
		return "movePage";
	}
	
	//댓글 수정 - 사용자 인증
	@RequestMapping(value = "replyUpdateGet")
	public String update_GET(ReplyDTO replyDTO, Model model, HttpServletRequest request) throws Exception {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int page = Integer.parseInt(request.getParameter("page"));
		int range = Integer.parseInt(request.getParameter("range"));
		String password = request.getParameter("password");
		String url = null;
			
		try {
		
			replyDTO.setBid(bid);
			replyDTO.setUid(uid);
			
			String result = replyService.replyPassword(replyDTO, password);
			
			if(result.equals("success"))
			{
				//수정 페이지(replyUpdateForm) 
				url = "replyUpdateForm?bid=" + bid + "&uid=" + uid + "&page=" + page + "&range=" + range;
			}
			else
			{
				url = "boardView?bid=" + bid + "&page=" + page + "&range=" + range;
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("replyUpdate() Error!!!");
		}
		
		return "redirect:" + url;
	}
	
	//댓글 수정 화면 - board/replyUpdate.jsp
	@RequestMapping("replyUpdateForm")
	public String replyUpdateForm(ReplyDTO replyDTO, Model model, HttpServletRequest request) {

		int uid = Integer.parseInt(request.getParameter("uid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int page = Integer.parseInt(request.getParameter("page"));
		int range = Integer.parseInt(request.getParameter("range"));
		
		model.addAttribute("ReplyOne", replyService.ReplyOne(replyDTO));		//댓글 정보
		model.addAttribute("page", page);										//페이징
		model.addAttribute("range", range);										//페이징
		
		return "board/replyUpdate";
	}
	
	//댓글 수정
	@RequestMapping("replyUpdate")
	public String replyUpdate(ReplyDTO replyDTO, Model model, HttpServletRequest request) {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int page = Integer.parseInt(request.getParameter("page"));
		int range = Integer.parseInt(request.getParameter("range"));
		String comment = request.getParameter("comment");
		String password = request.getParameter("password");
		String url = null;
		
		try {
			
			//줄바꿈 처리
			comment = comment.replace("\r\n","<br>");
			
			replyDTO.setUid(uid);
			replyDTO.setComment(comment);
			replyService.ReplyUpdate(replyDTO);
			
			url = "boardView?bid=" + bid + "&uid=" + uid + "&page=" + page + "&range=" + range;
		}catch(Exception e) {
			e.printStackTrace();
			url = "boardList";				//Error 발생 시, 전체 게시글 페이지로 이동
		}
		
		return "redirect:" + url;
	}
	
	//댓글 삭제
	@RequestMapping("replyDelete")
	public String replyDelete(ReplyDTO replyDTO, HttpServletRequest request) {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int page = Integer.parseInt(request.getParameter("page"));
		int range = Integer.parseInt(request.getParameter("range"));
		String password = request.getParameter("password");
		String result = null;
		
		try {
			
			replyDTO.setUid(uid);
			replyDTO.setBid(bid);
			
			result = replyService.replyPassword(replyDTO, password);
			if(result.equals("success"))
			{
				//replyService.ReplyDelete(replyDTO);
				
				//해당 게시글로 이동
			}
			else
			{
			
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("replyDelete() Error!!!");
		}
		
		return "redirect:boardView?bid=" + bid + "&page=" + page + "&range=" + range;
	}
}
