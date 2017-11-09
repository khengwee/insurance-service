package com.kiwi.insurance.web.domain.repository;

import com.kiwi.insurance.web.domain.model.ParentConfigDto;

import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;

public interface ConfigDtoRepository extends ResourceRepositoryV2<ParentConfigDto, String> {

	@Override
	public ParentConfigDto findOne(String id, QuerySpec querySpec);
	
	@Override
	public ResourceList<ParentConfigDto> findAll(QuerySpec querySpec);
}
