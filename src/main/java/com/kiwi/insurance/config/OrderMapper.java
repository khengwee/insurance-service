package com.kiwi.insurance.config;

import org.springframework.stereotype.Component;

import com.kiwi.insurance.spl.domain.model.Order;
import com.kiwi.insurance.web.domain.model.OrderDto;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class OrderMapper extends ConfigurableMapper {
	protected void configure(MapperFactory factory) {
		factory.classMap(Order.class, OrderDto.class)
			.byDefault()
            .register();
    }
}
