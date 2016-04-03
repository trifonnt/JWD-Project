INSERT INTO ws_user (id, user_name, password) VALUES (1, 'admin01', 'e10adc3949ba59abbe56e057f20f883e');

INSERT INTO ws_role (id, active, name) VALUES (1, 1, 'ROLE_USER');
INSERT INTO ws_role (id, active, name) VALUES (2, 1, 'ROLE_SHOP_EMPLOYEE');

INSERT INTO ws_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO ws_user_role (user_id, role_id) VALUES (1, 2);

INSERT INTO ws_product_type (id, name) VALUES (1, 'New Product');
INSERT INTO ws_product_type (id, name) VALUES (2, 'Used Product');
INSERT INTO ws_product_type (id, name) VALUES (3, 'Standard Service');
INSERT INTO ws_product_type (id, name) VALUES (4, 'Express Service');