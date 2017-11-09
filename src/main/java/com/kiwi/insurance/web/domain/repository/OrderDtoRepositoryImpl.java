package com.kiwi.insurance.web.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kiwi.insurance.fbs.service.OrderService;
import com.kiwi.insurance.web.domain.model.OrderDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderDtoRepositoryImpl extends ResourceRepositoryBase<OrderDto, String> implements OrderDtoRepository {

	@Autowired
	private OrderService orderService;
	
	public OrderDtoRepositoryImpl() {
		super(OrderDto.class);
	}

	@Override
	public synchronized OrderDto findOne(String id, QuerySpec querySpec) {
		OrderDto orderDto = orderService.getOrder(id);
		log.info("orderDto: {}", orderDto);
		return orderDto;
	}
	
	@Override
	public synchronized ResourceList<OrderDto> findAll(QuerySpec querySpec) {
		Iterable<OrderDto> ordersDto = orderService.getOrders();
		log.info("ordersDto: {}", ordersDto);
		return querySpec.apply(ordersDto);
	}

}
