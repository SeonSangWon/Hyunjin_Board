package com.hyunjin.main.Controller;

import javax.servlet.http.HttpServletRequest;

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

	/**
	 * 메인 홈페이지
	 * return main.jsp
	 */
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String home(BoardDTO boardDTO, Model model) throws Exception{
		logger.info("Welcome home! Hyunjin!!");
			
		//jsp:include 메인페이지 안에서 보이는  5개의 게시글
		model.addAttribute("list", boardService.BoardListMain());
			
		return "main";
	}
	
	/**
	 * 게시글 전체목록을 조회
	 * @param page		페이징
	 * @param range		페이징
	 * @pramg msg		게시글 등록/수정/삭제 메시지
	 * return boardList.jsp
	 */
	@RequestMapping(value="boardList", method=RequestMethod.GET)
	public String boardList(BoardDTO boardDTO,
			Model model,
			HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range) 
			throws Exception {
		
		String msg = "";
		
		//전체 페이지 갯수
		int listCnt = boardService.BoardListCount();
		
		//Pagination 객체생성
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("listAll", boardService.BoardList(pagination));
		model.addAttribute("msg", request.getParameter(msg));
				
		return "board/boardList";
	}
	
	/**
	 * 게시글 상세 페이지로 이동하며 해당 게시글의 조회수 + 1
	 * @param bid		게시글번호
	 * @param page		페이징
	 * @param range		페이징
	 * @param refUpdate	조회수 중복 증가를 막는 변수
	 * return boardView.jsp
	 */
	@RequestMapping(value = "boardView", method = RequestMethod.GET)
	public String boardView(BoardDTO boardDTO, ReplyDTO replyDTO, HttpServletRequest request, Model model) {
		
		//초기화
		int bid = 1;
		int page = 1;
		int range = 1;
		String refUpdate = "";
		try {
			bid = Integer.parseInt(request.getParameter("bid"));
			page = Integer.parseInt(request.getParameter("page"));
			range = Integer.parseInt(request.getParameter("range"));
			refUpdate = request.getParameter("msg");
			
			boardDTO.setBid(bid);
			
			if(refUpdate == null)
			{
				//게시글의 조회수 + 1
				boardService.BoardRefUpdate(boardDTO);
			}
			model.addAttribute("view", boardService.BoardView(boardDTO));
			model.addAttribute("replyView", replyService.ReplyView(replyDTO));
			model.addAttribute("page", page);
			model.addAttribute("range", range);
			logger.info("게시글 상세정보 조회");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("boardView Error!!!");
		}
		
		return "board/boardView";
	}
	
	/**
	 * 게시글을 등록 후, 게시글 전체목록으로 이동
	 * @param boardDTO
	 * @return boardList.jsp
	 */
	@RequestMapping(value="insertBoard", method=RequestMethod.POST)
	public String boardInsert(BoardDTO boardDTO, Model model) {

		try {
			//줄바꿈
			boardDTO.setComment(boardDTO.getComment().replace("\r\n","<br>"));
			
			boardService.BoardInsert(boardDTO);
			model.addAttribute("msg", "1");
			logger.info("게시글 등록");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("boardInsert Error!!!");
			model.addAttribute("msg", "2");
		}
		
		return "redirect:boardList";
	}
	
	/**
	 * 게시글 내용 수정
	 * @param boardDTO
	 * @param page		페이징
	 * @param range		페이징
	 * @return boardView.jsp
	 */
	@RequestMapping(value = "updateBoard", method = RequestMethod.POST)
	public String boardUpdate(BoardDTO boardDTO, HttpServletRequest request, Model model) throws Exception {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int range = Integer.parseInt(request.getParameter("range"));
		
		try {
			//줄바꿈
			boardDTO.setComment(boardDTO.getComment().replace("\r\n","<br>"));
			
			boardService.BoardUpdate(boardDTO);
			model.addAttribute("msg", "3");
			model.addAttribute("bid", "bid");
			model.addAttribute("page", page);
			model.addAttribute("range", range);
			logger.info("게시글 수정");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("boardUpdate Error!!!");
			model.addAttribute("msg", "4");
			model.addAttribute("bid", "bid");
			model.addAttribute("page", page);
			model.addAttribute("range", range);
			model.addAttribute("update", "fail");
		}
		
		return "redirect:boardView";
	}
	
	/**
	 * 게시글을 삭제 후, 게시글 목록 페이지로 이동
	 * @param boardDTO
	 * @return boardList.jsp
	 */
	@RequestMapping(value = "deleteBoard", method = RequestMethod.GET)
	public String boardDelete(BoardDTO boardDTO, Model model) throws Exception {
			
		try {
			boardService.BoardDelete(boardDTO);
			
			model.addAttribute("msg", "5");
			logger.info("게시글 삭제");
			
		}catch(Exception e) {
			e.printStackTrace();
			
			logger.info("boardDelete Error!!!");
			model.addAttribute("msg", "6");
		}
			
		return "redirect:boardList";
	}
	
}
