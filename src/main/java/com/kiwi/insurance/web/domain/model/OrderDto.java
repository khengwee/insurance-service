package com.kiwi.insurance.web.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

@Data
@JsonApiResource(type = "orders")
public class OrderDto {

	@JsonApiId
	private String orderId;
	
	private String orderStatus;
	private String title;
	private String firstName;
	private String lastName;
	private String nricPassNo;
	private String nricPassIssueCountry;
	private String residenceCountry;
	private String gender;
	private Date birthDate;
	private String address;
	private Integer postalCode;
	private String contactNo;
	private String email;
	private BigDecimal income;
	private String occupation;
	private String policyId;
	private Date startDate;
	private Date endDate;
	private BigDecimal premiumAount;
}
