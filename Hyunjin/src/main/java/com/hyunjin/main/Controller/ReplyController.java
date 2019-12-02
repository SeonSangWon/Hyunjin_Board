package com.hyunjin.main.Controller;

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
	
	//댓글 수정
	@RequestMapping(value = "replyUpdate", method = RequestMethod.POST)
	public String update_POST(ReplyDTO replyDTO, Model model, HttpServletRequest request) {
			
		int uid = 0;
		uid = Integer.parseInt(request.getParameter("uid"));
		
		try {
			System.out.println("=====" + uid + "=====");
			
			replyService.ReplyUpdate(replyDTO);
			logger.info("=====Reply Data Update=====");
				
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", replyDTO.getBid());
				
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("ReplyUpdate() Error!!!");
			
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", replyDTO.getBid());
		}
		
		return "movePage";
	}
	
	/*
	//댓글 수정
	@RequestMapping(value = "replyUpdateGet", method = RequestMethod.GET)
	public String update_GET(ReplyVO vo, Model model, HttpServletRequest request) {
				
		int uid = 0;
		int bid = 0;
		uid = Integer.parseInt(request.getParameter("uid"));
		bid = Integer.parseInt(request.getParameter("bid"));
		
		//비밀번호 체킹
		String password = request.getParameter("password");
			
		try {
		
			//prompt값으로 받아온 비밀번호 값으로 맞다면 수정 틀리다면 Error
			Date date = new Date();
			
			vo.setReg_date(date);
			vo.setPassword(password);
			vo.setComment("안녕하세요 수정1");
			service.replyUpdate(vo);
			
			logger.info("=====Reply Data Update=====");
				
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", vo.getBid());
				
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("replyUpdate() Error!!!");
			
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", vo.getBid());
			model.addAttribute("msg", "updateFail");
		}
		
		return null;
	}
	*/
}
