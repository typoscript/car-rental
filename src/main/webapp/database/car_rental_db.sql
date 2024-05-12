-- DCL: GRANT REVOKE
CREATE USER "car_rental_ops"@"localhost" IDENTIFIED BY "123321";

GRANT SELECT, INSERT, CREATE, DELETE, UPDATE ON `car_rental_db`.* TO "car_rental_ops"@"localhost";

-- database 생성
CREATE SCHEMA car_rental_db;
CREATE DATABASE car_rental_db;

USE car_rental_db;

SHOW tables;

select * from users;

DROP TABLE rental_reservations;

CREATE TABLE users (
	id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(40) NOT NULL,
	address VARCHAR(200) NOT NULL,
    phone CHAR(13) NOT NULL UNIQUE CHECK(`phone` REGEXP '^[0-9]{3}-[0-9]{4}-[0-9]{4}$'),
	is_admin TINYINT NOT NULL,
    reg_date DATE NOT NULL DEFAULT(NOW())
);

CREATE TABLE board (
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(280) NOT NULL,
    is_notice TINYINT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT(NOW()),
    modification_date TIMESTAMP NOT NULL DEFAULT(NOW()) ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE car_brands (
	name VARCHAR(30) PRIMARY KEY,
    creation_date TIMESTAMP NOT NULL DEFAULT(NOW())
);

CREATE TABLE cars (
	id INT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    type VARCHAR(10) NOT NULL CHECK(type IN ("세단", "경차", "SUV", "밴", "트럭")),
    fuel_type VARCHAR(10) NOT NULL CHECK(fuel_type IN ("가솔린", "디젤", "LPG", "하이브리드", "전기", "수소")),
    year YEAR NOT NULL,
    img_url VARCHAR(2048) NOT NULL,
    fee INT NOT NULL,
    mileage INT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT(NOW()),
	FOREIGN KEY (brand) REFERENCES car_brands(name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rental_reservations (
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id VARCHAR(20) NOT NULL,
    car_id INT NOT NULL,
    start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	status VARCHAR(20) NOT NULL CHECK(status IN ("예약", "만료", "취소")),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE ON UPDATE CASCADE
);