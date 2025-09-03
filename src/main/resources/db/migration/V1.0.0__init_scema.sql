CREATE TABLE employees (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(255),
age INTEGER,
gender VARCHAR(255),
salary FLOAT(53),
company_id BIGINT,
active tinyint(1),
PRIMARY KEY (id)
);


create table companies (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);