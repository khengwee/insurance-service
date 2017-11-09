package com.kiwi.insurance.web.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

@Data
@JsonApiResource(type = "products")
public class ProductDto {

	@JsonApiId
	private long id;
	private String name;

	public ProductDto(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
