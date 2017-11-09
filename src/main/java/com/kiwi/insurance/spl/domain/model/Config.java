package com.kiwi.insurance.spl.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CONFIG")
public class Config {

	@Id
	@GenericGenerator(
		name = "config-sequence",
		strategy = "com.kiwi.insurance.spl.domain.model.CustomSequenceIdentifier",
		parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence_name", value = "CONFIG_SEQ"),
			@org.hibernate.annotations.Parameter(name = "sequence_prefix", value = "CFG")
		}
	)
	@GeneratedValue(generator = "config-sequence", strategy = GenerationType.AUTO)
	@Column(name = "CONFIG_ID")
	private String configId;
	
	@Column(name = "CONFIG_TYPE")
	private String configType;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SEQUENCE")
	private int sequence;
	
	@Column(name = "IS_ACTIVE")
	@Type(type = "yes_no")
	private boolean isActive;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
}
