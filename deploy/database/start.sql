CREATE DATABASE IF NOT EXISTS justclickdb CHARACTER SET = 'utf8';

CREATE TABLE users(
	id int NOT NULL AUTO_INCREMENT,
	identification varchar(15) NOT NULL,
	firstname varchar(100) NOT NULL,
	lastname varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	username varchar(100) NOT NULL,
	max_value int NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);

INSERT INTO users(identification, firstname, lastname, email, username, max_value)
VALUES('0928956218', 'Jesús', 'Sarmiento', 'jesus_sarmi1994@hotmail.com', 'sarmi1994', 5);

INSERT INTO users(identification, firstname, lastname, email, username, max_value)
VALUES('0928956211', 'John', 'Smith', 'john_smith@hotmail.com', 'smith1122', 5);

INSERT INTO users(identification, firstname, lastname, email, username, max_value)
VALUES('0928956212', 'María', 'López', 'mairal@hotmail.com', 'maria1984', 5);

INSERT INTO users(identification, firstname, lastname, email, username, max_value)
VALUES('0928956213', 'Carlos', 'Martínez', 'cmartinez@hotmail.com', 'cmartinez', 5);

INSERT INTO users(identification, firstname, lastname, email, username, max_value)
VALUES('0928956214', 'Zulay', 'Moreira', 'zulay@hotmail.com', 'zulaym', 5);
