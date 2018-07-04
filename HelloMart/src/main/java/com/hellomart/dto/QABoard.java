<<<<<<< HEAD
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
=======
package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QABoard {

	private int idx;
	private String subject;
	private String content;
	private String id;
	private int reref;
	private int relev;
	private int reseq;
	private int count;
	private Date date;
	private Date modate;

}
>>>>>>> branch 'jjw' of https://github.com/oJJW111/HelloMart.git
