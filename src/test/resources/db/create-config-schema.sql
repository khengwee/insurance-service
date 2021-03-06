CREATE TABLE TBL_CONFIG
( 
	CONFIG_ID				VARCHAR(50), 
	CONFIG_TYPE 			VARCHAR(50), 
	CODE					VARCHAR(10),
 	DESCRIPTION				VARCHAR(255),
   	SEQUENCE				NUMBER,
	IS_ACTIVE				VARCHAR(1),
	CREATED_BY          	VARCHAR(50),
  	CREATED_DATE			TIMESTAMP,
    UPDATED_BY              VARCHAR(50),
 	UPDATED_DATE		    TIMESTAMP,
  	PRIMARY KEY (CONFIG_ID)
);

CREATE SEQUENCE CONFIG_SEQ;