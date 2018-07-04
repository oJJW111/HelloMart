package com.hellomart.controller;

import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.CmtBoard;
import com.hellomart.dto.QABoard;
import com.hellomart.service.CmtBoardService;
import com.hellomart.service.QABoardService;

@Controller
public class QABoardController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardController.class);
	
	@Autowired
	private QABoardService service;
	
	@Autowired
	private CmtBoardService service2;
	
	@RequestMapping("/qaboard")
	public ModelAndView qaBoardList(
			@RequestParam(value="page", required=false) Integer page) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = service.list(page);
		
		mav.addAllObjects(map);
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
	public ModelAndView view(int idx, String cmtnum) {
		
		ModelAndView mav = new ModelAndView();
		service.viewCount(idx);
		QABoard view = service.viewQABoard(idx);
		
		//화면에 보여질 게시글의갯수를 지정
		int pageSize=5;
		int startPage=0;
		int endPage=0;
		int pageBlock=0;
		int pageCount=service2.cmtCount(idx);
	
		
		//처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
		if(cmtnum == null){
			cmtnum="1";
		}
		
		//현재 보여지는 페이지 넘버값
		int currentPage  = Integer.parseInt(cmtnum);
		
		int startRow = (currentPage-1)*pageSize;
		
		Vector<CmtBoard> cmtlist = null;
		
		//게시글이 존재한다면
		if(pageCount >0){
			//10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
			cmtlist = service2.cmtlist(startRow, pageSize);
			pageCount=pageCount/pageSize+(pageCount%pageSize==0?0:1);
			pageBlock=3;
			
			startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
			
			endPage=startPage+pageBlock-1;
					
				if(endPage > pageCount){
					
					endPage = pageCount;
				}
			
		}
		mav.addObject("pageSize", pageSize);
		mav.addObject("pageBlock", pageBlock);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("pageCount", pageCount);
		mav.addObject("view",view);
		mav.addObject("cmtlist", cmtlist);
		mav.setViewName("qaboard/QAView");
		
		return mav;	
				
	}
	@RequestMapping(value = "/cmtinsert", method=RequestMethod.POST)
	public String cmtinsertProcess(int idx, CmtBoard cmtboard) {
		service2.cmtinsert(idx,cmtboard);
		return "location.reload";
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
