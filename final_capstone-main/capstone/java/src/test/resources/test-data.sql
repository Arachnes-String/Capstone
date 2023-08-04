BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','user4','ROLE_USER');

INSERT INTO drivers(driver_id, first_name, last_name, zip, radius) VALUES ('3', 'user3', 'guy', '33333', '50');
INSERT INTO drivers(driver_id, first_name, last_name, zip, radius) VALUES ('4', 'jeff', 'geff', '44444', '25');

INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('1', 'street', 'city', 'ST', '11111');
INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('2', 'street2', 'city2', 'AT', '22222');
INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('3', 'guyWithNoBinsHouse', 'city2', 'AT', '22222');


INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('1', 'first', 'last', '1', 'phone', 'user1@gmail.com');
INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('2', 'first2', 'last2', '2', 'phone', 'user2@gmail.com');
INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('3', 'guyWith', 'NoBin', '3', 'phone', 'user2@gmail.com');

INSERT INTO bins(client_id, clear_bins, green_bins, brown_bins, total_weight, credits, rejections) VALUES ('1','2','1','1','35.01','35','0');
INSERT INTO bins(client_id, clear_bins, green_bins, brown_bins, total_weight, credits, rejections) VALUES ('2','0','0','1','10.00','10','0');

COMMIT TRANSACTION;
