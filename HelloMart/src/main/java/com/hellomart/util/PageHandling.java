package com.hellomart.util;

import org.springframework.ui.Model;

public class PageHandling {
	private int totalRecord;
	private int nowPage;
	private int nowBlock;
	private int numPerPage;
	private int pagePerBlock;
	
	public PageHandling(int totalRecord, int nowPage, int nowBlock, 
			int numPerPage, int pagePerBlock){
		this.totalRecord = totalRecord;
		this.nowPage = nowPage;
		this.nowBlock = nowBlock;
		this.numPerPage = numPerPage;
		this.pagePerBlock = pagePerBlock;
	}
	
	public void setPageValue(Model model){
		// 총 페이지 수
		int totalPage = (int) Math.ceil((double) totalRecord / pagePerBlock);
				
		// 총 블럭 수
		int totalBlock = (int)Math.ceil((double)totalPage / pagePerBlock);
			
		// 각 페이지의 시작 인덱스 구하기 
		int beginPerPage = nowPage * numPerPage;
				
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("pagePerBlock", pagePerBlock);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalBlock", totalBlock);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("nowBlock", nowBlock);
		model.addAttribute("beginPerPage", beginPerPage);
		
//		System.out.println("totalRecord : " + totalRecord);
//		System.out.println("numPerPage : " + numPerPage);
//		System.out.println("pagePerBlock : " + pagePerBlock);
//		System.out.println("totalPage : " + totalPage);
//		System.out.println("nowPage : " + nowPage);
//		System.out.println("nowBlock : " + nowBlock);
//		System.out.println("beginPerPage : " + beginPerPage);
	}
}


