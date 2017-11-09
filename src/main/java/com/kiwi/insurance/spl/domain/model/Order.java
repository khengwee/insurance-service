package com.kiwi.insurance.spl.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_ORDER")
public class Order {

	@Id
	@GenericGenerator(
		name = "order-sequence",
		strategy = "com.kiwi.insurance.spl.domain.model.CustomSequenceIdentifier",
		parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "ORDER_SEQ"),
			@org.hibernate.annotations.Parameter(name = "sequence_prefix", value = "ORD")
		}
	)
	@GeneratedValue(generator = "order-sequence", strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "NRIC_PASS_NO")
	private String nricPassNo;
	
	@Column(name = "NRIC_PASS_ISSUE_COUNTRY")
	private String nricPassIssueCountry;
	
	@Column(name = "RESIDENCE_CTRY")
	private String residenceCountry;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "POSTAL_CODE")
	private Integer postalCode;
	
	@Column(name = "CONTACT_NO")
	private String contactNo;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "INCOME")
	private BigDecimal income;
	
	@Column(name = "OCCUPATION")
	private String occupation;

	@Column(name = "POLICY_ID")
	private String policyId;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "PREMIUM_AMOUNT")
	private BigDecimal premiumAount;

	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
}
