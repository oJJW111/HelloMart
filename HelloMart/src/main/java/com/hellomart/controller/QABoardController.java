package com.hellomart.controller;

import java.util.Vector;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
   public ModelAndView qaBoardList(String pageNum) {
      ModelAndView mav = new ModelAndView();

      // 화면에 보여질 게시글의갯수를 지정
      int pageSize = 5;
      int startPage = 0;
      int endPage = 0;
      int pageBlock = 0;
      int pageCount = service.getCount();
      int qacount = service.getCount();

      // 처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
      if (pageNum == null) {
         pageNum = "1";
      }

      // 현재 보여지는 페이지 넘버값
      int currentPage = Integer.parseInt(pageNum);

      int startRow = (currentPage - 1) * pageSize;

      Vector<QABoard> list = null;

      // 게시글이 존재한다면
      if (pageCount > 0) {
         // 10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
         list = service.listQABoard(startRow, pageSize);
         pageCount = pageCount / pageSize + (pageCount % pageSize == 0 ? 0 : 1);
         pageBlock = 3;

         startPage = ((currentPage / pageBlock) - (currentPage % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;

         endPage = startPage + pageBlock - 1;

         if (endPage > pageCount) {

            endPage = pageCount;
         }

      }

      mav.addObject("pageSize", pageSize);
      mav.addObject("pageBlock", pageBlock);
      mav.addObject("startPage", startPage);
      mav.addObject("endPage", endPage);
      mav.addObject("pageCount", pageCount);
      mav.addObject("list", list);
      mav.addObject("qacount", qacount);
      mav.setViewName("qaboard/QABoardList");

      return mav;
   }

   @RequestMapping(value = "/qasearch", method=RequestMethod.GET)
   public ModelAndView qaSearchList(@Param("pagenum")String pageNum, 
                           @Param("searchOption")String searchOption, 
                           @Param("keyword")String keyword) {
      
      ModelAndView mav = new ModelAndView();
      
      //화면에 보여질 게시글의갯수를 지정
      int pageSize=5;
      int startPage=0;
      int endPage=0;
      int pageBlock=0;
      int subjectCount=service.subjectCount("%" + keyword + "%");
      int contentCount=service.contentCount("%" + keyword + "%");
      int idCount=service.idCount("%" + keyword + "%");
      int qacount=service.getCount();
      
      //처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
      if(pageNum == null){
         pageNum="1";
      }
      
      //현재 보여지는 페이지 넘버값
      int currentPage  = Integer.parseInt(pageNum);
      
      int startRow = (currentPage-1)*pageSize;
      
      Vector<QABoard> list = null;

      
      //게시글이 존재한다면
      if(searchOption.equals("subject")) {
         if(subjectCount >0){
         //10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
            list = service.listQABoardSub("%" + keyword + "%", startRow, pageSize);
         }
         subjectCount=subjectCount/pageSize+(subjectCount%pageSize==0?0:1);
         pageBlock=3;
         
         startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
         
         endPage=startPage+pageBlock-1;
               
            if(endPage > subjectCount){
               
               endPage = subjectCount;
         
            mav.addObject("subjectCount", subjectCount);
            }
      
      }else if (searchOption.equals("content")) {
         if(contentCount >0){
            //10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
            list = service.listQABoardCon("%" + keyword + "%", startRow, pageSize);
            }
            contentCount=contentCount/pageSize+(contentCount%pageSize==0?0:1);
            pageBlock=3;
            
            startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
            
            endPage=startPage+pageBlock-1;
                  
               if(endPage > contentCount){
                  
                  endPage = contentCount;
                  
                  mav.addObject("contentCount", contentCount);
               }

      }else if (searchOption.equals("id")) {
         if(idCount >0){
            //10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
            list = service.listQABoardId("%" + keyword + "%", startRow, pageSize);
            }
            idCount=idCount/pageSize+(idCount%pageSize==0?0:1);
            pageBlock=3;
         
            startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
         
            endPage=startPage+pageBlock-1;
               
            if(endPage > idCount){
               
               endPage = idCount;
               mav.addObject("idCount", idCount);
            }
            
   }
   
      mav.addObject("searchOption", searchOption);
      mav.addObject("keyword", keyword);
      mav.addObject("pageSize", pageSize);
      mav.addObject("pageBlock", pageBlock);
      mav.addObject("startPage", startPage);
      mav.addObject("endPage", endPage);
      mav.addObject("list",  list);
      mav.addObject("qacount", qacount);
      mav.setViewName("qaboard/QABoardList");
      
      return mav;
   }

   @RequestMapping(value = "/write", method = RequestMethod.GET)
   public ModelAndView write() {
      return new ModelAndView("qaboard/QAWrite", "qaboard", new QABoard());
   }

   @RequestMapping(value = "/write", method = RequestMethod.POST)
   public String writeProcess(@ModelAttribute("qaboard") @Valid QABoard qaboard, BindingResult bindingResult) {
      
      //오류여부 확인
      if(bindingResult.hasErrors()){
         return "qaboard/QAWrite";
      }else{
      
      service.insertQABoard(qaboard);
      return "redirect:/qaboard";
      }
   }

   @RequestMapping(value = "/view", method = RequestMethod.GET)
   public ModelAndView view(int idx, String cmtnum) {
      
      ModelAndView mav = new ModelAndView();
      CmtBoard cmtboard = new CmtBoard();
      service.viewCount(idx);
      QABoard view = service.viewQABoard(idx);

      // 화면에 보여질 게시글의갯수를 지정
      int pageSize = 5;
      int startPage = 0;
      int endPage = 0;
      int pageBlock = 0;
      int pageCount = service2.cmtCount(idx);

      // 처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
      if (cmtnum == null) {
         cmtnum = "1";
      }
      // 현재 보여지는 페이지 넘버값
      int currentPage = Integer.parseInt(cmtnum);

      int startRow = (currentPage - 1) * pageSize;

      Vector<CmtBoard> cmtlist = null;
      // 게시글이 존재한다면
      if (pageCount > 0) {
         // 10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
         cmtlist = service2.cmtlist(idx, startRow, pageSize);
         pageCount = pageCount / pageSize + (pageCount % pageSize == 0 ? 0 : 1);
         pageBlock = 3;

         startPage = ((currentPage / pageBlock) - (currentPage % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;

         endPage = startPage + pageBlock - 1;

         if (endPage > pageCount) {

            endPage = pageCount;
         }

      }
      mav.addObject("pageSize", pageSize);
      mav.addObject("pageBlock", pageBlock);
      mav.addObject("startPage", startPage);
      mav.addObject("endPage", endPage);
      mav.addObject("pageCount", pageCount);
      mav.addObject("view", view);
      mav.addObject("cmtlist", cmtlist);
      mav.addObject("cmtboard", cmtboard);
      mav.setViewName("qaboard/QAView");

      return mav;

   }

   @RequestMapping(value = "/cmtinsert", method = RequestMethod.POST)
   public String cmtinsertProcess(@ModelAttribute("cmtboard") @Valid CmtBoard cmtboard, BindingResult bindingResult) {
      int idx = cmtboard.getCmtpar();

      //오류여부 확인
      if(bindingResult.hasErrors()){
         return "redirect:/view?idx=" + idx;
      }else{
         service2.cmtinsert(cmtboard);
         service.cmtinc(idx);
         return "redirect:/view?idx=" + idx;
      }
   }

   @RequestMapping(value = "/cmtdelete", method = RequestMethod.GET)
   public String cmtdeleteProcess(int cmtidx, int idx) {
      service2.cmtdelete(cmtidx);
      service.cmtdec(idx);
      return "redirect:/view?idx=" + idx;
   }

   @RequestMapping(value = "/modify", method = RequestMethod.GET)
   public ModelAndView modify(int idx) {

      ModelAndView mav = new ModelAndView();
      QABoard qaboard = service.viewQABoard(idx);
      mav.addObject("qaboard", qaboard);
      mav.setViewName("qaboard/modify");
      return mav;
   }

   @RequestMapping(value = "/modify", method = RequestMethod.POST)
   public String modifyProcess(QABoard qaboard) {
      service.modify(qaboard);
      return "redirect:/qaboard";
   }

   @RequestMapping(value = "/delete", method = RequestMethod.GET)
   public String deleteProcess(int idx) {
      service.delete(idx);
      return "redirect:/qaboard";
   }
}