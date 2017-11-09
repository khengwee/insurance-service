package com.kiwi.insurance.web.domain.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kiwi.insurance.web.domain.model.ProductDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class ProductDtoRepository extends ResourceRepositoryBase<ProductDto, Long> {

	private Map<Long, ProductDto> products = new HashMap<>();
	
	public ProductDtoRepository() {
		super(ProductDto.class);
		products.put(1L, new ProductDto(1L, "Travel"));
		products.put(2L, new ProductDto(2L, "Personal Accident"));
		products.put(3L, new ProductDto(3L, "Fire"));
		products.put(4L, new ProductDto(4L, "Life"));
	}

	@Override
	public synchronized ResourceList<ProductDto> findAll(QuerySpec querySpec) {
		return querySpec.apply(products.values());
	}
}
