CREATE DATABASE IF NOT EXISTS sgpzf;

USE sgpzf;

CREATE TABLE IF NOT EXISTS stack(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_stack PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS skill(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_skill PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS stack_skill(
	idskill INT NOT NULL,
    idstack INT NOT NULL,
    idstatus INT NOT NULL,
    CONSTRAINT pk_stack_skill PRIMARY KEY(idskill, idstack),
    CONSTRAINT fk_stack_skill_skill FOREIGN KEY(idskill) REFERENCES skill(id) ON DELETE CASCADE,
    CONSTRAINT fk_stack_skill_stack FOREIGN KEY(idstack) REFERENCES stack(id) ON DELETE CASCADE
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
    CONSTRAINT fk_region_country FOREIGN KEY(idcountry) REFERENCES country(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS city(
	id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    idregion INT NOT NULL,
    CONSTRAINT pk_city PRIMARY KEY(id),
    CONSTRAINT fk_city_region FOREIGN KEY(idregion) REFERENCES region(id) ON DELETE CASCADE
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
    CONSTRAINT fk_persons_city FOREIGN KEY(idcity) REFERENCES city(id) ON DELETE CASCADE,
    CONSTRAINT fk_persons_gender FOREIGN KEY(idgender) REFERENCES gender(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS persons_skill(
	id INT NOT NULL AUTO_INCREMENT,
    registration_date DATE NOT NULL,
    iperson INT NOT NULL,
    idskill INT NOT NULL,
    CONSTRAINT pk_persons_skill PRIMARY KEY(id),
    CONSTRAINT fk_persons_skill_persons FOREIGN KEY(iperson) REFERENCES persons(id) ON DELETE CASCADE,
    CONSTRAINT fk_persons_skill_skill FOREIGN KEY(idskill) REFERENCES skill(id) ON DELETE CASCADE
);

-- /////////

DROP PROCEDURE IF EXISTS createPerson;

DELIMITER $$
CREATE PROCEDURE createPerson(
    IN new_name VARCHAR(50),
    IN new_lastname VARCHAR(50),
    IN new_idcity INT,
    IN new_address VARCHAR(50),
    IN new_age INT,
    IN new_email VARCHAR(100),
    IN new_idgender INT
)
BEGIN
    INSERT INTO persons(id, name, lastname, idcity, address, age, email, idgender)
    VALUES (NULL, new_name, new_lastname, new_idcity, new_address, new_age, new_email, new_idgender);
END $$
DELIMITER ;

-- ////////

DROP PROCEDURE IF EXISTS asignarHabilidadPersona;

DELIMITER $$
CREATE PROCEDURE asignarHabilidadPersona(
    IN new_date DATE,
    IN new_idperson INT,
    IN new_idskill INT
)
BEGIN
    INSERT INTO persons_skill(id, registration_date, iperson, idskill)
    VALUES (NULL, new_date, new_idperson, new_idskill);
END $$
DELIMITER ;

-- /////////

DROP PROCEDURE IF EXISTS CheckIdExistsINT;

DELIMITER $$

CREATE PROCEDURE CheckIdExistsINT(
	IN tableName VARCHAR(64), 
    IN columnName VARCHAR(64), 
    IN idValue INT
)
BEGIN
    SET @query = CONCAT('SELECT EXISTS(SELECT 1 FROM ', tableName, ' WHERE ', columnName, ' = ?) AS id_exists');
    PREPARE stmt FROM @query;
    SET @idValue = idValue;
    EXECUTE stmt USING @idValue;
    DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;


-- //////////
