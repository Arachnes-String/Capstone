BEGIN TRANSACTION;


DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS drivers CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS pick_ups CASCADE;
DROP TABLE IF EXISTS bins CASCADE;
DROP TABLE IF EXISTS prize CASCADE;
DROP TABLE IF EXISTS prize_client CASCADE;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE addresses (

    address_id SERIAL PRIMARY KEY,
    client_id int,
    street_address varchar(200) NOT NULL,
    city varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    zip varchar(50) NOT NULL,

    CONSTRAINT FK_user_address FOREIGN KEY (client_id) REFERENCES users (user_id)

);


CREATE TABLE clients (

    client_id int PRIMARY KEY,
    first_name varchar(50),
    last_name varchar(50),
    address_id int,
    phone_number varchar(14),
    email_address varchar(50),

    CONSTRAINT FK_user_user_info FOREIGN KEY (client_id) REFERENCES users (user_id),
    CONSTRAINT FK_user_info_address FOREIGN KEY (address_id) REFERENCES addresses (address_id)
);

CREATE TABLE drivers (

    driver_id int PRIMARY KEY,
    first_name varchar(50),
    last_name varchar(50),
    zip varchar(50) NOT NULL,
    radius numeric(3, 1),


	CONSTRAINT FK_user_driver_info FOREIGN KEY (driver_id) REFERENCES users (user_id)

);


CREATE TABLE pick_ups (

    pick_up_id SERIAL PRIMARY KEY,
    date_time_created TIMESTAMP,
    date_time_requested TIMESTAMP,
    date_time_completed TIMESTAMP,
    address_id int,
    client_id int,
    driver_id int,
    status TEXT,

    clear_bin_full boolean,
    green_bin_full boolean,
    brown_bin_full boolean,

    weight numeric(9, 3),

    --to-do: add constraint that status needs to be either Pending, Completed, or Rejected
    CONSTRAINT min_one_full_bin CHECK (clear_bin_full = TRUE OR green_bin_full = TRUE OR brown_bin_full = TRUE),
    CONSTRAINT FK_client_pick_up FOREIGN KEY (client_id) REFERENCES users (user_id),
    CONSTRAINT FK_driver_pick_up FOREIGN KEY (driver_id) REFERENCES users (user_id),
    CONSTRAINT FK_address_pick_up FOREIGN KEY (address_id) REFERENCES addresses (address_id)

);

    CREATE TABLE bins (

        client_id int PRIMARY KEY,
        clear_bins int,
        green_bins int,
        brown_bins int,
        total_weight numeric (9, 3) DEFAULT 0,
        credits int,
        rejections int,

        CONSTRAINT FK_client_bin FOREIGN KEY (client_id) REFERENCES clients (client_id)
    );

    CREATE TABLE prize (
        prize_id SERIAL PRIMARY KEY,
        name text NOT NULL, 
        credit_value int NOT NULL
    );

     CREATE TABLE prize_client (
        prize_client_id SERIAL PRIMARY KEY,
        prize_id int,
        client_id int,

        CONSTRAINT FK_client_prize FOREIGN KEY (client_id) REFERENCES clients (client_id),
        CONSTRAINT FK_prize_prize FOREIGN KEY (prize_id) REFERENCES prize (prize_id)
    );

COMMIT TRANSACTION;
