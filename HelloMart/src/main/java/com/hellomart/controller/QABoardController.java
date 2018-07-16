package com.hellomart.controller;

import java.util.Map;
import java.util.Vector;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.CmtBoard;
import com.hellomart.dto.QABoard;
import com.hellomart.service.CmtBoardService;
import com.hellomart.service.QABoardService;

@Controller
@RequestMapping("/qaboard")
public class QABoardController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QABoardController.class);
	
	@Autowired
	private QABoardService service;
	
	@Autowired
	private CmtBoardService service2;


   	@RequestMapping("/qaboardList")
   	public ModelAndView qaBoardList(
		   @RequestParam(value="page", required=false) Integer page,
		   @RequestParam(value="searchOption", required=false) String searchOption,
		   @RequestParam(value="keyword", required=false) String keyword) {
   		ModelAndView mav = new ModelAndView();

   		final int MAXRESULT = 5;
   		final int PAGEPERBLOCK = 5;
   		Map<String, Object> map =  service.list(page, MAXRESULT, PAGEPERBLOCK, searchOption, keyword);

   		mav.addAllObjects(map);
   		mav.setViewName("qaboard/QABoardList");

   		return mav;
   	}

   	@PreAuthorize("isAuthenticated()")
   	@RequestMapping(value = "/qawrite", method = RequestMethod.GET)
   	public ModelAndView write() {
	    return new ModelAndView("qaboard/QAWrite", "qaboard", new QABoard());
   	}

   	@PreAuthorize("isAuthenticated()")
   	@RequestMapping(value = "/qawrite", method = RequestMethod.POST)
   	public String writeProcess(@ModelAttribute("qaboard") @Valid QABoard qaboard, BindingResult bindingResult) {
   		//오류여부 확인
   		if(bindingResult.hasErrors()){
   			return "qaboard/QAWrite";
   		} else{
   			service.insertQABoard(qaboard);
   			return "redirect:/qaboard/qaboardList";
   		}
   	}

   	@RequestMapping(value = "/qaview", method = RequestMethod.GET)
   	public ModelAndView qaview(int idx) {
   		ModelAndView mav = new ModelAndView();
      
   		service.viewCount(idx);
   		QABoard view = service.viewQABoard(idx);
   		
   		mav.addObject("view", view);
   		mav.setViewName("qaboard/QAView");

      return mav;
   }
   	@RequestMapping(value = "/CMTBoardList", method = RequestMethod.GET)
   	public ModelAndView qaview(@RequestParam(value="idx", required=false) Integer idx, 
   								@RequestParam(value="page", required=false) Integer page) {

   		ModelAndView mav = new ModelAndView();
   		
   		final int MAXRESULT = 5;
   		final int PAGEPERBLOCK = 5;
   		Map<String, Object> map = service2.cmtlist(idx, page, MAXRESULT, PAGEPERBLOCK);
   		mav.addAllObjects(map);
   		mav.addObject("idx", idx);
   		mav.addObject("cmtboard", new CmtBoard());
   		mav.setViewName("qaboard/CMTBoardList");
   		
   		return mav;
   	}
   

   	

   @RequestMapping(value = "/cmtinsert", method = RequestMethod.POST)
   public String cmtinsertProcess(@ModelAttribute("cmtboard") @Valid CmtBoard cmtboard, BindingResult bindingResult) {
      int idx = cmtboard.getCmtpar();

      //오류여부 확인
      if(bindingResult.hasErrors()){
         return "redirect:/qaboard/qaview?idx=" + idx;
      }else{
         service2.cmtinsert(cmtboard);
         service.cmtinc(idx);
         return "redirect:/qaboard/qaview?idx=" + idx;
      }
   }

   @RequestMapping(value = "/cmtdelete", method = RequestMethod.GET)
   public String cmtdeleteProcess(int cmtidx, int idx) {
      service2.cmtdelete(cmtidx);
      service.cmtdec(idx);
      return "redirect:/qaboard/qaview?idx=" + idx;
   }

   @RequestMapping(value = "/qamodify", method = RequestMethod.GET)
   public ModelAndView modify(int idx) {

      ModelAndView mav = new ModelAndView();
      QABoard qaboard = service.viewQABoard(idx);
      mav.addObject("qaboard", qaboard);
      mav.setViewName("qaboard/QAModify");
      return mav;
   }

   @RequestMapping(value = "/qamodify", method = RequestMethod.POST)
   public ModelAndView modifyProcess(@ModelAttribute("qaboard") @Valid QABoard qaboard, BindingResult bindingResult) {
	    ModelAndView mav = new ModelAndView();
	    
	   	//오류여부 확인
	      if(bindingResult.hasErrors()){
	    	  String check = "1";
	    	  mav.addObject("idx",qaboard.getIdx());
	    	  mav.addObject("check", check);
	    	  mav.setViewName("redirect:/qaboard/qamodify");
	    	  return mav;
	      }else{
	    	  service.modify(qaboard);
	    	  mav.setViewName("redirect:/qaboard/qaboardList");
	    	  return mav;
	      }
   }

   @RequestMapping(value = "/qadelete", method = RequestMethod.GET)
   public String deleteProcess(int idx) {
      service.delete(idx);
      return "redirect:/qaboard/qaboardList";
   }
   
}