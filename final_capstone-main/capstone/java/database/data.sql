BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('bob','$2a$10$NH/JazQFHIiool4JJ56Sxu8eQwBftkMYqubXy.FcB3Y1XqZVFijnS','ROLE_DRIVER');
INSERT INTO users (username,password_hash,role) VALUES ('jeff','$2a$10$1YvzgxO8v/7PhWTQMlJteeXZKWnCI.OTeGGzSOI.0diPNwckRLB3.', 'ROLE_DRIVER');
INSERT INTO users (username,password_hash,role) VALUES ('jane','$2a$10$nQ1JJZDcUfltLcqmKqXUj.Lqv6KZXH320OdvS.vE4kHhVuuClcule', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('nani','$2a$10$x1MiVFWC.JsMJG.Z2G58/udtsiQ2yjLFv4cID/SW.u/TXnaxSbUmO', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('charlotte','$2a$10$BtVkkxEV4kLLmG3nXnSHFuyxFWQ6rS.Zl.WymnqxC086k5udTtc1e', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('gael','$2a$10$QCYE/lsh.N1hSxk.6BF.4.PJQ4yTPRW2Zh7Sz4NegJN9u94Z1XJKy', 'ROLE_USER');

INSERT INTO drivers(driver_id, first_name, last_name, zip, radius) VALUES ('1', 'user', 'default', '00000', '0');
INSERT INTO drivers(driver_id, first_name, last_name, zip, radius) VALUES ('3', 'bob', 'guy', '43209', '50');
INSERT INTO drivers(driver_id, first_name, last_name, zip, radius) VALUES ('4', 'jeff', 'geff', '43017', '25');

INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('5', '5940 Muncie Ct', 'Dublin', 'Ohio', '43017');
INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('6', '7180 School Dr', 'Dublin', 'Ohio', '43017');
INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('7', '5745 Sells Mill Dr', 'Dublin', 'Ohio', '43017');
INSERT INTO addresses( client_id, street_address, city, state, zip) VALUES ('8', '2469 Elm Ave', 'Columbus', 'Ohio', '43209');

INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('5', 'Jane', 'Doe', '1', '4444322341', 'janedoe@gmail.com');
INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('6', 'Nani', 'Kalei', '2', '2029182132', 'naniKalei@gmail.com');
INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('7', 'Charlotte', 'Horst', '3', '5059753302', 'horst@gmail.com');
INSERT INTO clients(client_id, first_name, last_name, address_id, phone_number, email_address)VALUES ('8', 'Gael', 'Ann', '4', '5056050989', 'gaelawesome@gmail.com');

INSERT INTO bins(client_id, clear_bins, green_bins, brown_bins, total_weight, credits, rejections) VALUES ('5','2','1','1','35.20','35','0');
INSERT INTO bins(client_id, clear_bins, green_bins, brown_bins, total_weight, credits, rejections) VALUES ('6','0','0','1','10.56','10','0');
INSERT INTO bins(client_id, clear_bins, green_bins, brown_bins, total_weight, credits, rejections) VALUES ('7','1','1','0','22.84','22','1');


INSERT INTO pick_ups (date_time_created, date_time_requested, date_time_completed, address_id, client_id, driver_id, status, clear_bin_full, green_bin_full, brown_bin_full, weight)
VALUES
    ('2023-06-02 18:07:30', CURRENT_DATE + INTERVAL '3 day', '9999-12-31 23:59:59', 1, 5, 1, 'Pending', TRUE, TRUE, TRUE, 200.50),
    ('2023-06-02 18:15:45', CURRENT_DATE + INTERVAL '2 day', '9999-12-31 23:59:59', 2, 6, 4, 'Pending', FALSE, TRUE, FALSE, 122.4),
    ('2023-06-02 18:55:50', CURRENT_DATE + INTERVAL '1 day', '9999-12-31 23:59:59', 1, 5, 1, 'Pending', TRUE, TRUE, TRUE, 250.75),
    ('2023-06-02 19:05:15', CURRENT_DATE + INTERVAL '4 day', '9999-12-31 23:59:59', 2, 6, 4, 'Pending', FALSE, TRUE, FALSE, 180.6),
    ('2023-06-02 19:05:15', CURRENT_DATE + INTERVAL '3 day', '9999-12-31 23:59:59', 4, 8, 4, 'Pending', FALSE, TRUE, FALSE, 55.8),
    ('2023-06-02 19:05:15', CURRENT_DATE + INTERVAL '2 day', '9999-12-31 23:59:59', 4, 8, 4, 'Pending', FALSE, TRUE, FALSE, 45.6),
    ('2023-06-02 19:45:10', CURRENT_DATE + INTERVAL '1 day', '9999-12-31 23:59:59', 1, 5, 1, 'Pending', TRUE, TRUE, TRUE, 190.25),
    ('2023-06-02 19:55:25', CURRENT_DATE + INTERVAL '4 day', '9999-12-31 23:59:59', 2, 6, 4, 'Pending', FALSE, TRUE, FALSE, 110.8),
    ('2023-06-02 20:05:40', CURRENT_DATE + INTERVAL '2 day', '9999-12-31 23:59:59', 3, 7, 4, 'Pending', TRUE, FALSE, TRUE, 95.50),
    ('2023-06-02 20:15:55', CURRENT_DATE + INTERVAL '1 day', '9999-12-31 23:59:59', 4, 8, 3, 'Pending', FALSE, FALSE, TRUE, 70.25),
    ('2023-06-02 18:25:10', '2023-06-02 19:40:00', '2023-06-03 19:40:00', 3, 7, 4, 'Completed', TRUE, FALSE, TRUE, 130.50),
    ('2023-06-02 18:35:20', '2023-06-02 19:50:00', '2023-06-03 19:50:00', 4, 8, 3, 'Completed', FALSE, FALSE, TRUE, 55.5),
    ('2023-06-02 18:45:35', '2023-06-02 20:00:00', '2023-06-03 20:00:00', 4, 8, 3, 'Rejected', TRUE, TRUE, FALSE, 300.0),
    ('2023-06-02 19:15:30', '2023-06-02 19:50:00', '2023-06-03 19:50:00', 3, 7, 4, 'Completed', TRUE, FALSE, TRUE, 75.25),
    ('2023-06-02 19:25:45', '2023-06-02 19:40:00', '2023-06-03 19:50:00', 4, 8, 3, 'Completed', FALSE, FALSE, TRUE, 90.75),
    ('2023-06-02 19:35:55', '2023-06-02 20:00:00', '2023-06-03 20:00:00', 4, 8, 3, 'Rejected', TRUE, TRUE, FALSE, 150.0);

INSERT INTO prize(name, credit_value) VALUES
('Glass Roots T-Shirt', 300), 
('Coke Bottle Lamp', 700),
('Glass Countertop', 2500);

COMMIT TRANSACTION;
