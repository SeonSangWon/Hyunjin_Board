package com.hyunjin.main.Controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@RequestMapping(value = "replyUpdateGet")
	public String update_GET(ReplyDTO replyDTO, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int uid = 0;
		int bid = 0;
		uid = Integer.parseInt(request.getParameter("uid"));
		bid = Integer.parseInt(request.getParameter("bid"));
		
		//비밀번호 체킹
		String password = request.getParameter("password");
			
		try {
		
			//prompt값으로 받아온 비밀번호 값으로 맞다면 수정 틀리다면 Error
			replyDTO.setBid(bid);
			replyDTO.setUid(uid);
			String getPassword = replyService.replyUpdatePassword(replyDTO);
			
			if(getPassword.equals(password))
			{
				model.addAttribute("url", "replyUpdateGet");
				model.addAttribute("bid", bid);
			}
			else
			{
				out.println("<script>"
						+ "alert('비밀번호가 일치하지 않습니다.');"
						+ "history.back();"
	        			+ "</script>");
	            out.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("replyUpdate() Error!!!");
		}
		
		return "movePage";
	}
	
	@RequestMapping("replyUpdate")
	public String replyUpdate(ReplyDTO replyDTO, Model model, HttpServletRequest request) {
		
		String comment = request.getParameter("comment");
		int uid = Integer.parseInt(request.getParameter("uid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		//현재 시각 구하기
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd kk:mm:ss");
		Calendar cal = Calendar.getInstance();
		String time = null;
		time = formatter.format(cal.getTime());
		Timestamp reg_date = Timestamp.valueOf(time);
		System.out.println("댓글 변경 시각 :  " + reg_date);
		
		try {
			
			replyDTO.setUid(uid);
			replyDTO.setComment(comment);
			replyDTO.setReg_date(reg_date);
			
			replyService.ReplyUpdate(replyDTO);
			
			model.addAttribute("bid", bid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:boardView";
	}
	
	//댓글 삭제
	@RequestMapping("replyDelete")
	public String replyDelete(ReplyDTO replyDTO, HttpServletRequest request) {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		try {
					
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("replyDelete() Error!!!");
		}
		
		return "";
	}
}
