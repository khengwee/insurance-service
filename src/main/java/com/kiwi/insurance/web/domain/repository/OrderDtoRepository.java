package com.kiwi.insurance.web.domain.repository;

import com.kiwi.insurance.web.domain.model.OrderDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;

public interface OrderDtoRepository extends ResourceRepositoryV2<OrderDto, String> {
	
	@Override
	public OrderDto findOne(String id, QuerySpec querySpec);
	
	@Override
	public ResourceList<OrderDto> findAll(QuerySpec querySpec);
}
