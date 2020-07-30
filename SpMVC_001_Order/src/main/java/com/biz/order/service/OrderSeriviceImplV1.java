package com.biz.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.order.dao.OrderDao;
import com.biz.order.model.OrderVO;

@Service
public class OrderSeriviceImplV1 implements OrderService {

	@Autowired
	private OrderDao orderDao;
		
	@Override
	public List<OrderVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * seq값을 파라메터로 받아서
	 * OrderDao.findBySeq(seq) 호출하고 DB로부터 전달되어온
	 * 주문서 1개 레코드를 orderVO에 받고
	 * 호출한 곳으로 그대로 return 구조
	 */
	@Override
	public OrderVO fineBySeq(long seq) {

		// 34
		OrderVO orderVO = orderDao.fineBySeq(seq);
		return orderVO;
		
	}

	@Override
	public int insert(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
