package com.hyunjin.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//게시글 작성 페이지 이동
	@RequestMapping(value = "boardWrite")
	public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardWrite";
	}
	
}
