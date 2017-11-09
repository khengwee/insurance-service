package com.kiwi.insurance.config;

import org.springframework.stereotype.Component;

import com.kiwi.insurance.spl.domain.model.Product;
import com.kiwi.insurance.web.domain.model.ProductDto;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ProductMapper extends ConfigurableMapper {
	protected void configure(MapperFactory factory) {
		factory.classMap(Product.class, ProductDto.class)
			.byDefault()
            .register();
    }
}
