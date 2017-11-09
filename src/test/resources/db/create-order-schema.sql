CREATE TABLE TBL_ORDER 
( 
	ORDER_ID					VARCHAR(50), 
	ORDER_STATUS 			VARCHAR(50), 
	TITLE					VARCHAR(50),
 	FIRST_NAME				VARCHAR(255),
   	LAST_NAME				VARCHAR(255),
	NRIC_PASS_NO				VARCHAR(50),
	NRIC_PASS_ISSUE_COUNTRY	VARCHAR(50),
  	RESIDENCE_CTRY			VARCHAR(50),
	GENDER					VARCHAR(1),
  	BIRTH_DATE				DATE,
	ADDRESS					VARCHAR(500),
 	POSTAL_CODE				INTEGER,
  	CONTACT_NO				VARCHAR(50),
  	EMAIL					VARCHAR(50),
  	INCOME					NUMBER,
 	OCCUPATION				VARCHAR(50),
   	POLICY_ID				VARCHAR(50),
	START_DATE				DATE,
	END_DATE					DATE,
	PREMIUM_AMOUNT			NUMBER,
 	CREATED_DATE				TIMESTAMP,
 	UPDATED_DATE				TIMESTAMP,
  	PRIMARY KEY (ORDER_ID)
);

CREATE SEQUENCE ORDER_SEQ;