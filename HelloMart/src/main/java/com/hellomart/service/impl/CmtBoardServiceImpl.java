package com.hellomart.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.CmtBoardDAO;
import com.hellomart.dto.CmtBoard;
import com.hellomart.dto.QABoard;
import com.hellomart.service.CmtBoardService;
import com.hellomart.util.Paging;

@Service
public class CmtBoardServiceImpl implements CmtBoardService{
	
   
   @SuppressWarnings("unused")
private static final Logger logger = LoggerFactory.getLogger(CmtBoardService.class);
   
   @Autowired
   private CmtBoardDAO dao;
   
   
	private Paging paging(Integer page, int maxResult, int pagePerBlock, int idx) {
		page = page == null ? 1 : page;
		
		
		int total = dao.getCount(idx);
		
		return new Paging(total, page, maxResult, pagePerBlock);
	}
   
	private Vector<CmtBoard> cmtlist(int idx, int offset, int limit) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("offset", offset);
		paramMap.put("limit", limit);
		paramMap.put("cmtpar", idx);
		
		return dao.cmtlist(paramMap);
	}

	@Override
	public Map<String, Object> cmtlist(Integer idx, Integer page, int maxResult, int pagePerBlock) {
		Map<String, Object> modelMap = new HashMap<>();

		Paging paging = paging(page, maxResult, pagePerBlock, idx);
		modelMap.put("paging", paging);
		
		int offset = paging.getOffset();
		Vector<CmtBoard> cmtlist = null;
		if(offset != -1) {
			int limit = paging.getMaxResult();
			cmtlist = cmtlist(idx, offset, limit);
		}
		modelMap.put("cmtlist", cmtlist);
		
		return modelMap;
	}


   @Override
   public void cmtinsert(CmtBoard cmtboard) {
      dao.cmtinsert(cmtboard);
   }


   @Override
   public int cmtCount(int idx) {
      return dao.cmtCount(idx);
   }

   @Override
   public void cmtdelete(int cmtidx) {
      dao.cmtdelete(cmtidx);      
   }

}