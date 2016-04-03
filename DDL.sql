CREATE TABLE ws_user (
	id NUMBER,
	user_name VARCHAR2(20),
	password VARCHAR2(100),
	account_non_expired CHAR DEFAULT 1,
	account_non_locked CHAR DEFAULT 1,
	credentials_non_expired CHAR DEFAULT 1,
	enabled CHAR DEFAULT 1,

	first_name VARCHAR2(40),
	middle_name VARCHAR2(40),
	last_name VARCHAR2(40),
	email VARCHAR2(100),
	email_verified CHAR DEFAULT 0,

	phone VARCHAR2(20),
	phone_verified CHAR DEFAULT 0,

	  CONSTRAINT ws_user_pk PRIMARY KEY (id)
	, CONSTRAINT ws_user_acct_non_expired_chk CHECK (account_non_expired in (0,1))
	, CONSTRAINT ws_user_acct_non_locked_chk CHECK (account_non_locked in (0,1))
	, CONSTRAINT ws_user_creds_non_exp_chk CHECK (credentials_non_expired in (0,1))
	, CONSTRAINT ws_user_enabled_chk CHECK (enabled in (0,1))

	, CONSTRAINT ws_user_email_verified_chk CHECK (email_verified in (0,1))
	, CONSTRAINT ws_user_phone_verified_chk CHECK (phone_verified in (0,1))
);


CREATE TABLE ws_role (
	id NUMBER,
	active CHAR DEFAULT 1,
	name VARCHAR2(100) NOT NULL,

	  CONSTRAINT ws_role_pk PRIMARY KEY (id)
	, CONSTRAINT ws_role_active_chk CHECK (active in (0,1))
);

CREATE TABLE ws_user_role (
	user_id NUMBER NOT NULL,
	role_id NUMBER NOT NULL,
	CONSTRAINT ws_user_role_pk PRIMARY KEY (user_id, role_id)
);
ALTER TABLE ws_user_role
ADD CONSTRAINT ws_user_role_user_id
FOREIGN KEY (user_id) REFERENCES ws_user(id);

ALTER TABLE ws_user_role
ADD CONSTRAINT ws_user_role_role_id
FOREIGN KEY (role_id) REFERENCES ws_role(id);

--ALTER TABLE ws_user_role
--ADD CONSTRAINT ws_user_role_unq
--UNIQUE (user_id, role_id);


CREATE TABLE ws_product_type (
	id NUMBER,
	name VARCHAR2(40),
	active CHAR(1) DEFAULT 1,
	
	  CONSTRAINT ws_product_type_pk PRIMARY KEY (id)
	, CONSTRAINT ws_product_type_active_chk CHECK (active in (0,1))
);
ALTER TABLE ws_product_type
ADD CONSTRAINT ws_product_type_unq
UNIQUE (name);

CREATE TABLE ws_product (
	id NUMBER,
	prd_number VARCHAR2(40) NOT NULL,
	name VARCHAR2(255) NOT NULL,
	description VARCHAR2(255),
	type_id NUMBER,
	active CHAR(1) DEFAULT 1, 
	price NUMBER,
	qty_on_hand NUMBER,
	creator_id NUMBER,

	  CONSTRAINT ws_product_pk PRIMARY KEY (id)
	, CONSTRAINT ws_product_active_chk CHECK (active in (0,1))
);
ALTER TABLE ws_product
ADD CONSTRAINT ws_product_unq
UNIQUE (prd_number);

ALTER TABLE ws_product
ADD CONSTRAINT ws_product__type_id
FOREIGN KEY (type_id) REFERENCES ws_product_type(id);

ALTER TABLE ws_product
ADD CONSTRAINT ws_product__creator_id
FOREIGN KEY (creator_id) REFERENCES ws_user(id);
