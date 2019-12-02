package com.hyunjin.main.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyunjin.main.DTO.BoardDTO;
import com.hyunjin.main.DTO.ReplyDTO;
import com.hyunjin.main.Service.IBoardService;
import com.hyunjin.main.Service.IReplyService;
import com.hyunjin.main.util.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private IBoardService boardService;
	
	@Autowired
	private IReplyService replyService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//main.jsp (메인 페이지)
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String home(BoardDTO boardDTO, Model model) throws Exception{
		logger.info("Welcome home! Hyunjin!!");
			
		//jsp:include 메인페이지 안에서 보이는  5개의 게시글
		model.addAttribute("list", boardService.BoardListMain());
			
		return "main";
	}
	
	//board/boardList.jsp (게시글 전체목록 조회)
	//페이징
	@RequestMapping(value="boardList", method=RequestMethod.GET)
	public String boardList(BoardDTO boardDTO,
			Model model,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range) 
			throws Exception {
		
		//전체 페이지 갯수
		int listCnt = boardService.BoardListCount();
		
		//Pagination 객체생성
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("listAll", boardService.BoardList(pagination));
		
		return "board/boardList";
	}
	
	//게시글 상세 페이지 이동
	@RequestMapping(value = "boardView", method = RequestMethod.GET)
	public String boardView(BoardDTO boardDTO, ReplyDTO replyDTO, HttpServletRequest request, Model model) {
		
		int bid = 0;
		bid = Integer.parseInt(request.getParameter("bid"));
		
		try {
			
			boardDTO.setBid(bid);
			
			model.addAttribute("view", boardService.BoardView(boardDTO));
			model.addAttribute("replyView", replyService.ReplyView(replyDTO));
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("boardView Error!!!");
		}
		
		return "board/boardView";
	}
	
	//게시글 등록
	@RequestMapping(value="insertBoard", method=RequestMethod.POST)
	public String insert(BoardDTO boardDTO, HttpServletResponse response, Model model) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			//vo.setComment(vo.getComment().replace("\r\n","<br>"));
			
			boardService.BoardInsert(boardDTO);
			model.addAttribute("url", "insertBoard");
			
			
			out.println("<script>"
					+ "alert('게시글이 정상적으로 등록되었습니다.');"
        			+ "</script>");
            out.flush();
			
		}catch(Exception e) {
			out.println("<script>"
					+ "alert('알 수 없는 오류가 발생했습니다. 잠시 후 시도해주세요.');"
					+ "history.back();"
        			+ "</script>");
            out.flush();
		}
		
		return "movePage";
	}
	
	//게시글 수정
	@RequestMapping(value = "updateBoard", method = RequestMethod.POST)
	public String update(BoardDTO boardDTO, HttpServletResponse response, Model model) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			boardService.BoardUpdate(boardDTO);
			model.addAttribute("url", "updateBoard");
			
			out.println("<script>"
					+ "alert('게시글이 수정되었습니다.');"
        			+ "</script>");
            out.flush();
			
		}catch(Exception e) {
			out.println("<script>"
					+ "alert('알 수 없는 오류가 발생했습니다. 잠시 후 시도해주세요.');"
					+ "history.back();"
        			+ "</script>");
            out.flush();
		}
		
		return "movePage";
	}
	
	//게시글 클릭 시, 조회수 +1
	@RequestMapping(value = "refUpdate", method = RequestMethod.GET)
	public String refUpdate(BoardDTO boardDTO, HttpServletRequest request, Model model) throws Exception {
		
		int bid = 0;
		bid = Integer.parseInt(request.getParameter("bid"));
		
		try {
			boardDTO.setBid(bid);
			boardService.BoardRefUpdate(boardDTO);
			
			model.addAttribute("url", "boardView");
			model.addAttribute("bid", bid);
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("ref() Error!!!");	
		}
		
		return "movePage";
	}
	
	//게시글 삭제
	@RequestMapping(value = "deleteBoard", method = RequestMethod.POST)
	public String delete(BoardDTO boardDTO, HttpServletResponse response, Model model) throws Exception {
			
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
			
		try {
			boardService.BoardDelete(boardDTO);
			model.addAttribute("url", "deleteBoard");
			
			out.println("<script>"
					+ "alert('게시글이 삭제되었습니다.');"
	       			+ "</script>");
	           out.flush();
				
		}catch(Exception e) {
			out.println("<script>"
					+ "alert('알 수 없는 오류가 발생했습니다. 잠시 후 시도해주세요.');"
					+ "history.back();"
	       			+ "</script>");
	           out.flush();
		}
			
		return "movePage";
	}
	
}
