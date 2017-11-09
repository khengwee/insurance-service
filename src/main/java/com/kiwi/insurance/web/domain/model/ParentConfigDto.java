package com.kiwi.insurance.web.domain.model;

import java.util.List;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

@Data
@JsonApiResource(type = "configs")
public class ParentConfigDto {

	@JsonApiId
	private String configType;
	private List<ConfigDto> configs;
	
}
