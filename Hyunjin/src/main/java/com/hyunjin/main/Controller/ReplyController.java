package com.hyunjin.main.Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyunjin.main.DTO.ReplyDTO;
import com.hyunjin.main.Service.IReplyService;

@Controller
public class ReplyController {

	@Autowired
	private IReplyService replyService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	/**
	 * 댓글 등록
	 * @param replyDTO
	 * @param bid		게시글 번호
	 * @param page		페이징
	 * @param range 	페이징
	 * @param msg		댓글 등록 메시지
	 * @return boardView.jsp
	 */
	@ResponseBody
	@RequestMapping("replyInsert")
	public ReplyDTO replyInsert(@ModelAttribute ReplyDTO vo) {
		
		try{
			ReplyDTO replyDTO = replyService.replyInsert(vo);
			
		}catch(Exception e) {
			//Error	
		}
		return replyDTO;
	}
	
	/*
	@RequestMapping(value = "replyInsert", method = RequestMethod.POST)
	public String replyInsert(ReplyDTO replyDTO, Model model, HttpServletRequest request) {
		
		//초기화
		int page = 1;
		int range = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			range = Integer.parseInt(request.getParameter("range"));
			
			replyService.ReplyInsert(replyDTO);
			
			model.addAttribute("bid", replyDTO.getBid());
			model.addAttribute("page", page);
			model.addAttribute("range", range);
			model.addAttribute("msg", "1");
			logger.info("댓글 등록");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("replyInsert Error!!!");
			model.addAttribute("bid", replyDTO.getBid());
			model.addAttribute("page", page);
			model.addAttribute("range", range);
			model.addAttribute("msg", "2");
		}
		
		return "redirect:boardView";
	}
	*/

	
	/**
	 * 댓글 수정 시, 비밀번호 인증
	 * @param bid		게시글번호
	 * @param uid		댓글번호
	 * @param page		페이징
	 * @param range		페이징
	 * @param password	댓글 비밀번호
	 * @param result	비밀번호 유효 체킹
	 * @param url		리턴할 URL
	 * @return replyUpdateForm()
	 */
	@RequestMapping(value = "passwordValidate")
	public String passwordValidate(ReplyDTO replyDTO, Model model, HttpServletRequest request) throws Exception {
		
		//초기화
		int bid = 1;
		int uid = 1;
		int page = 1;
		int range = 1;
		String password = "";
		String result = "";
		String url = "";
		try {
			bid = Integer.parseInt(request.getParameter("bid"));
			uid = Integer.parseInt(request.getParameter("uid"));
			page = Integer.parseInt(request.getParameter("page"));
			range = Integer.parseInt(request.getParameter("range"));
			password = request.getParameter("password");
			
			replyDTO.setBid(bid);
			replyDTO.setUid(uid);
			
			//사용자가 입력한 비밀번호 체킹
			result = replyService.ReplyPassword(replyDTO, password);
			
			if(result.equals("success"))
			{
				//비밀번호가 일치할 경우
				//param : bid / uid / page / range / msg
				url = "replyUpdateForm?bid=" + bid + "&uid=" + uid + "&page=" + page + "&range=" + range;
			}
			else
			{
				//비밀번호가 틀렸을 경우
				//param : bid / page / range
				url = "boardView?bid=" + bid + "&page=" + page + "&range=" + range;
				model.addAttribute("msg", "9");
			}
			logger.info("댓글 비밀번호 체킹");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("passwordValidate Error!!!");
			url = "boardList";
			model.addAttribute("bid", bid);
			model.addAttribute("page", page);
			model.addAttribute("range", range);
			model.addAttribute("msg", "9");
		}
		
		return "redirect:" + url;
	}
	
	/**
	 * 댓글 수정 페이지 이동
	 * @param bid		게시글번호
	 * @param uid		댓글번호
	 * @param page		페이징
	 * @param range		페이징
	 * @return replyUpdate.jsp
	 */
	@RequestMapping(value="replyUpdateForm", method=RequestMethod.GET)
	public String replyUpdateForm(ReplyDTO replyDTO, Model model, HttpServletRequest request) {

		//초기화
		int bid = 1;
		int uid = 1;
		int page = 1;
		int range = 1;
		try {
			bid = Integer.parseInt(request.getParameter("bid"));
			uid = Integer.parseInt(request.getParameter("uid"));
			page = Integer.parseInt(request.getParameter("page"));
			range = Integer.parseInt(request.getParameter("range"));
			
			replyDTO.setBid(bid);
			replyDTO.setUid(uid);
			
			model.addAttribute("ReplyOne", replyService.ReplyOne(replyDTO));		//댓글 정보
			model.addAttribute("page", page);										//페이징
			model.addAttribute("range", range);										//페이징
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("replyUpdateForm Error!!!");
			return "redirect:/";
		}
		
		return "board/replyUpdate";
	}
	
	/**
	 * 댓글 수정
	 * @param replyDTO
	 * @param page		페이징
	 * @param range		페이징
	 * @return boardView.jsp
	 */
	@RequestMapping("replyUpdate")
	public String replyUpdate(ReplyDTO replyDTO, Model model, HttpServletRequest request) {
		
		//초기화
		int bid = 1;
		int uid = 1;
		int page = 1;
		int range = 1;
		String url = "";
		try {
			uid = Integer.parseInt(request.getParameter("uid"));
			bid = Integer.parseInt(request.getParameter("bid"));
			page = Integer.parseInt(request.getParameter("page"));
			range = Integer.parseInt(request.getParameter("range"));
			
			replyDTO.setUid(uid);
			replyService.ReplyUpdate(replyDTO);
			
			//param : bid / uid / page / range
			url = "boardView?bid=" + bid + "&uid=" + uid + "&page=" + page + "&range=" + range;
			model.addAttribute("msg", "3");
			logger.info("댓글 수정");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("replyUpdate Error!!!");
			url = "boardView?bid=" + bid + "&uid=" + uid + "&page=" + page + "&range=" + range;
			model.addAttribute("msg", "4");
		}
		
		return "redirect:" + url;
	}
	
	/**
	 * 댓글 삭제
	 * @param replyDTO
	 * @param page		페이징
	 * @param range		페이징
	 * @param password	비밀번호
	 * @param result	비밀번호 유효 체킹
	 * @return
	 */
	@RequestMapping("replyDelete")
	public String replyDelete(ReplyDTO replyDTO, HttpServletRequest request, Model model) {
		
		int bid = 1;
		int uid = 1;
		int page = 1;
		int range = 1;
		String password = "";
		String result = "";
		try {
			bid = Integer.parseInt(request.getParameter("bid"));
			uid = Integer.parseInt(request.getParameter("uid"));
			page = Integer.parseInt(request.getParameter("page"));
			range = Integer.parseInt(request.getParameter("range"));
			password = request.getParameter("password");
			
			replyDTO.setUid(uid);
			replyDTO.setBid(bid);
			
			//사용자가 입력한 비밀번호 체킹
			result = replyService.ReplyPassword(replyDTO, password);
			if(result.equals("success"))
			{
				//비밀번호가 일치할 경우
				replyService.ReplyDelete(replyDTO);
				model.addAttribute("msg", "5");
			}
			else
			{
				//비밀번호가 틀렸을 경우
				model.addAttribute("msg", "6");
			}
			logger.info("댓글 삭제");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("replyDelete Error!!!");
		}
		
		return "redirect:boardView?bid=" + bid + "&page=" + page + "&range=" + range;
	}

}
