CREATE DATABASE schoolDatabase;
--USE schoolDatabase;
\c schoolDatabase

CREATE TYPE Rank AS ENUM ('FACILITY','STAFF','STUDENT');
CREATE TABLE Person (
	id				BIGSERIAL		NOT NULL	PRIMARY KEY,
	rank			Rank			NOT NULL,
	first_name 		VARCHAR(100)	NOT NULL,
	middle_name		VARCHAR(100),
	last_name		VARCHAR(100)	NOT NULL
);

CREATE TABLE Term (
	id				BIGSERIAL		NOT	NULL	PRIMARY KEY,
	term			VARCHAR(15)		NOT NULL
);

CREATE TABLE Class (
	id				BIGSERIAL		NOT NULL	PRIMARY KEY,
	name			VARCHAR(100)	NOT NULL,
	room_number		VARCHAR(20)		NOT NULL,
	period			SMALLINT		NOT NULL,
	term			BIGINT			NOT NULL	REFERENCES		Term(id)		ON DELETE CASCADE
);

CREATE TABLE PersonClass (
	person_id		BIGINT			NOT NULL 	REFERENCES		Person(id)		ON DELETE CASCADE,
	class_id		BIGINT			NOT NULL 	REFERENCES		Class(id)		ON DELETE CASCADE
);
--CREATE INDEX personclass_person_id_seq ON PersonClass (person_id);
--CREATE INDEX personclass_class_id_seq ON PersonClass (class_id);