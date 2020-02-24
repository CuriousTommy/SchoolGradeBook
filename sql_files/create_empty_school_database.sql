CREATE DATABASE schoolDatabase;
--USE schoolDatabase;
\c schoolDatabase

CREATE TYPE Rank AS ENUM ('FACILITY','STAFF','STUDENT');
CREATE TABLE Person (
	id				BIGSERIAL					PRIMARY KEY,
	rank			Rank			NOT NULL,
	first_name 		VARCHAR(100)	NOT NULL,
	middle_name		VARCHAR(100),
	last_name		VARCHAR(100)	NOT NULL
);

