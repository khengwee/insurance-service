package com.kiwi.insurance.fbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.insurance.config.OrderMapper;
import com.kiwi.insurance.spl.domain.model.Order;
import com.kiwi.insurance.spl.domain.repository.OrderRepository;
import com.kiwi.insurance.web.domain.model.OrderDto;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	public OrderDto getOrder(String id) {
		Order order = orderRepository.findOne(id);
		return orderMapper.map(order, OrderDto.class);
	}
	
	public Iterable<OrderDto> getOrders() {
		Iterable<Order> orders = orderRepository.findAll();
		return orderMapper.mapAsList(orders, OrderDto.class);
	}

}
