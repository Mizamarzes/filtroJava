CREATE DATABASE IF NOT EXISTS sgpzf;

USE sgpzf;

CREATE TABLE IF NOT EXISTS stack(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_stack PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS skill(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_skill PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS stack_skill(
	idskill INT NOT NULL,
    idstack INT NOT NULL,
    idstatus INT NOT NULL,
    CONSTRAINT pk_stack_skill PRIMARY KEY(idskill, idstack),
    CONSTRAINT fk_stack_skill_skill FOREIGN KEY(idskill) REFERENCES skill(id),
    CONSTRAINT fk_stack_skill_stack FOREIGN KEY(idstack) REFERENCES stack(id)
);

CREATE TABLE IF NOT EXISTS gender(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
	CONSTRAINT pk_gender PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS country(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_country PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS region(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    idcountry INT NOT NULL,
    CONSTRAINT pk_region PRIMARY KEY(id),
    CONSTRAINT fk_region_country FOREIGN KEY(idcountry) REFERENCES country(id)
);

CREATE TABLE IF NOT EXISTS city(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    idregion INT NOT NULL,
    CONSTRAINT pk_city PRIMARY KEY(id),
    CONSTRAINT fk_city_region FOREIGN KEY(idregion) REFERENCES region(id)
);

CREATE TABLE IF NOT EXISTS persons(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    idcity INT NOT NULL,
    address VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    idgender INT NOT NULL,
    CONSTRAINT pk_persons PRIMARY KEY(id),
    CONSTRAINT fk_persons_city FOREIGN KEY(idcity) REFERENCES city(id),
    CONSTRAINT fk_persons_gender FOREIGN KEY(idgender) REFERENCES gender(id)
);

CREATE TABLE IF NOT EXISTS persons_skill(
	id INT NOT NULL AUTO_INCREMENT,
    registration_date DATE NOT NULL,
    iperson INT NOT NULL,
    idskill INT NOT NULL,
    CONSTRAINT pk_persons_skill PRIMARY KEY(id),
    CONSTRAINT fk_persons_skill_persons FOREIGN KEY(iperson) REFERENCES persons(id),
    CONSTRAINT fk_persons_skill_skill FOREIGN KEY(idskill) REFERENCES skill(id)
)