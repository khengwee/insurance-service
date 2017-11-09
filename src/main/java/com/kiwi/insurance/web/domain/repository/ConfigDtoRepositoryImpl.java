package com.kiwi.insurance.web.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kiwi.insurance.fbs.service.ConfigService;
import com.kiwi.insurance.web.domain.model.ParentConfigDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConfigDtoRepositoryImpl extends ResourceRepositoryBase<ParentConfigDto, String> implements ConfigDtoRepository {
	
	@Autowired
	private ConfigService configService;
	
	public ConfigDtoRepositoryImpl() {
		super(ParentConfigDto.class);
	}
	
	@Override
	public synchronized ParentConfigDto findOne(String id, QuerySpec querySpec) {
		ParentConfigDto configDto = configService.getConfigsByConfigType(id);
		log.info("configDto: {}", configDto);
		return configDto;
	}

	@Override
	public synchronized ResourceList<ParentConfigDto> findAll(QuerySpec querySpec) {
		Iterable<ParentConfigDto> configsDto = configService.getConfigs();
		log.info("configsDto: {}", configsDto);
		return querySpec.apply(configsDto);
	}
}
