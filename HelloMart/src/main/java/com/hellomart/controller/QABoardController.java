package com.hellomart.controller;




import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.QABoard;
import com.hellomart.service.QABoardService;

@Controller
public class QABoardController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardController.class);
	
	@Autowired
	private QABoardService service;
	
	@RequestMapping("/qaboard")
	public ModelAndView qaBoardList(String pageNum) {
		ModelAndView mav = new ModelAndView();
		
		//화면에 보여질 게시글의갯수를 지정
		int pageSize=10;
		
		int count =0;//전체 글의 갯수
		int number =0;//페이지 넘버링수(현재 화면에 보고있는 페이지 넘버 값)
		
		//처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
		if(pageNum == null){
			pageNum="1";
		}
		//현재 보여지는 페이지 넘버값
		int currentPage  = Integer.parseInt(pageNum);
		//게시글의 총 갯수 얻기
		count = service.getCount();
	
		//현제 페이지에 보여줄 시작 번호를 설정 = 데이터 베이스에서 불러올 시작 번호를 의미
		int startRow = (currentPage -1)*pageSize+1;
		int endRow = currentPage*pageSize;
		Vector<QABoard> list = null;
		
		System.out.println("startRow : " + startRow);
		System.out.println("endRow : " + endRow);
				
		//게시글이 존재한다면
		if(count > 0 ){
			//10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
			list = service.listQABoard(startRow-1 , endRow);		
			//테이블에 표시할 번호를 설정
			number = count -(currentPage -1) * pageSize;
			
		}
		//BoardList.jsp
		mav.addObject("pageSize", pageSize);
		mav.addObject("number", number);
		mav.addObject("count", count);
		mav.addObject("currentPage", currentPage);		
		mav.addObject("list",  list);
		mav.setViewName("qaboard/QABoardList");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public ModelAndView write() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qaboard/QAWrite");
		return mav;
	}
	
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String writeProcess(QABoard qaboard){
		
		service.insertQABoard(qaboard);
		
		return "redirect:/qaboard";
	}
	
	@RequestMapping(value = "/view", method=RequestMethod.GET)
	public ModelAndView view(int idx) {
		
		ModelAndView mav = new ModelAndView();
		service.viewCount(idx);
		QABoard view = service.viewQABoard(idx);
		mav.addObject("view",view);
		mav.setViewName("qaboard/QAView");
		
		return mav;
	}
	
	@RequestMapping(value = "/rewrite", method=RequestMethod.GET)
	public ModelAndView reWrite(int idx) {
		ModelAndView mav = new ModelAndView();
		QABoard qaboard = service.viewQABoard(idx);
		mav.addObject("qaboard", qaboard);
		mav.setViewName("qaboard/reWrite");
		return mav;
	}
		
	
	
	@RequestMapping(value = "/rewrite", method=RequestMethod.POST)
	public String rewriteProcess(QABoard qaboard) {
		service.reWrite(qaboard);
		return "redirect:/qaboard";
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public ModelAndView modify(int idx) {
		
		ModelAndView mav = new ModelAndView();
		QABoard qaboard = service.viewQABoard(idx);
		mav.addObject("qaboard", qaboard);
		mav.setViewName("qaboard/modify");
		return mav;
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modifyProcess(QABoard qaboard) {
		service.modify(qaboard);
		return "redirect:/qaboard";
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String deleteProcess(int idx) {
		service.delete(idx);
		return "redirect:/qaboard";
	}
	

	
	
	
}
