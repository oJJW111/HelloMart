package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductList {
	private int no;         
	private String productName;    
	private String mainCategory;     
	private String smallCategory;     
	private String imagePath;    
	private int orderCount;    
	private int score;     
	private String prodYear;
	private String prodMonth;
	private String prodDay;       
	private String mfCompany;   
	private int price;  
	private int weight;        
	private String registerID;     
	private String comment;     
	private Date registerDate;
}



