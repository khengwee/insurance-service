package com.kiwi.insurance.config;

import org.springframework.stereotype.Component;

import com.kiwi.insurance.spl.domain.model.Config;
import com.kiwi.insurance.web.domain.model.ConfigDto;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ConfigMapper extends ConfigurableMapper {
	protected void configure(MapperFactory factory) {
		factory.classMap(Config.class, ConfigDto.class)
			.byDefault()
            .register();
    }

}
