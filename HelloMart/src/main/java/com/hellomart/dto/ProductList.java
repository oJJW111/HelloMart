package com.hellomart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductList {
	private int No;         
	private String ProductName;    
	private String MainCategory;     
	private String SmallCategory;     
	private String ImagePath;    
	private int OrderCount;    
	private int Score;     
	private Date ProdDate;       
	private String MfCompany;   
	private int Price;  
	private int Weight;        
	private String RegisterID;     
	private String Comment;     
	private Date RegisterDate;
}
