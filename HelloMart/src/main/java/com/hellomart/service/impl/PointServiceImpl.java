package com.hellomart.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dao.PointDAO;
import com.hellomart.dto.OrderList;
import com.hellomart.dto.Point;
import com.hellomart.service.AccountService;
import com.hellomart.service.PointService;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	PointDAO pointDao;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	AccountService accountService;

	@Override
	public void insertPoint(HttpServletRequest request, OrderList orderList) {
		Point point = new Point();
		
		String incDec = request.getParameter("incDec");
		
		int totalPrice = orderList.getOrderPrice();
		int grade = accountService.get(orderList.getOrderId()).getGrade();
		double qty = 0; 
		
		String content = request.getParameter("prodName"); 
		if(incDec.equals("+")){
			qty = totalPrice * (0.01 * grade);
			content += "의 구매로" + qty + "만큼 " +"증가";	
		}
		if(incDec.equals("-")){
			qty = Double.parseDouble(request.getParameter("point"));
			content += "의 구매에 " + qty + "만큼 사용해서 " + "감소";
		}
		
		point.setId(orderList.getOrderId());
		point.setIncDec(incDec); 
		point.setPoint((int)Math.ceil(qty));
		point.setContent(content);
		
		pointDao.insertPoint(point); 
		accountDao.updatePoint(point.getId(), point.getPoint(), point.getIncDec());
	}

	@Override
	public void insertPointList(HttpServletRequest request) {
		Point point = new Point();

		String incDec = request.getParameter("incDec");
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int size = Integer.parseInt(request.getParameter("size"));
		String id = request.getParameter("orderId");
		
		int grade = accountService.get(id).getGrade();
		double qty = 0; 

		String content = request.getParameter("prodName0");
		if (incDec.equals("+")) {
			qty = totalPrice * (0.01 * grade);
			content += "외 " + size + "개의 상품의 구매로" + qty + "만큼 " + "증가";
		}
		if (incDec.equals("-")) {
			qty = Double.parseDouble(request.getParameter("point"));
			content += "외 " + size + "개의 상품의 구매에 " + qty + "만큼 사용해서 " + "감소";
		}

		point.setId(id);
		point.setIncDec(incDec);
		point.setPoint((int) Math.ceil(qty));
		point.setContent(content);

		pointDao.insertPoint(point);
		accountDao.updatePoint(point.getId(), point.getPoint(), point.getIncDec());
	}

	@Override
	public List<Point> getAllPointLog(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> getPeriodPointLog(String id, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
}
