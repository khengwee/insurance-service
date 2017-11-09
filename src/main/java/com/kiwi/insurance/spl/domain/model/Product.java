package com.kiwi.insurance.spl.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_PRODUCT")
public class Product {

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String productId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_STATUS")
	private String productStatus;
	
	@Column(name = "PROMOTION_CODE")
	private String promotionCode;
	
	@Column(name = "DISCOUNT_TYPE")
	private String discountType;
	
	@Column(name = "DISCOUNT_PERCENTAGE")
	private Float discountPercentage;
	
	@Column(name = "DISCOUNT_AMOUNT")
	private BigDecimal discountAmount;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
}
