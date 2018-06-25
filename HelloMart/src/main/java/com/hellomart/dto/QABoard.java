package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QABoard {

	private int idx;
	private String subject;
	private String content;
	private String id;
	private int reRef;
	private int reLev;
	private int reSeq;
	private int count;
	private Date date;

}