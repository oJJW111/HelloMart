package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CmtBoard {

	private int cmtidx;
	private String id;
	private Date date;
	private int cmtpar;
	private String content;

	

}
