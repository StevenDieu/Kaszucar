-- -----------------------------------------------------
-- Table address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS address (
  id_address SERIAL NOT NULL,
  address VARCHAR(1024) NULL,
  complement_address VARCHAR(1024) NULL,
  postal_code INT NULL,
  country VARCHAR(255) NULL,
  PRIMARY KEY (id_address));



-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
  id_users SERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  phone_number INT NULL,
  email_adress VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  ip_address VARCHAR(15) NOT NULL,
  genre VARCHAR(3) NOT NULL,
  year_of_birth INT NOT NULL,
  description VARCHAR(500) NULL,
  url_picture VARCHAR(1024) NULL,
  id_address INT NULL,
  PRIMARY KEY (id_users),
  CONSTRAINT fk_users_address1
    FOREIGN KEY (id_address)
    REFERENCES address (id_address)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE UNIQUE INDEX email_adress_UNIQUE ON users (email_adress ASC);

CREATE UNIQUE INDEX phone_number_UNIQUE ON users (phone_number ASC);

CREATE INDEX fk_users_address1_idx ON users (id_address ASC);


-- -----------------------------------------------------
-- Table cars
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cars (
  id_cars SERIAL NOT NULL,
  brand VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  comfort VARCHAR(45) NOT NULL,
  color VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_cars));



-- -----------------------------------------------------
-- Table preference
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS preference (
  id_preference SERIAL NOT NULL,
  smoking boolean NULL,
  animals boolean NULL,
  musics boolean NULL,
  detour boolean NULL,
  food boolean NULL,
  PRIMARY KEY (id_preference));



-- -----------------------------------------------------
-- Table covoiturage
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS covoiturage (
  id_covoiturage SERIAL NOT NULL,
  date_first_trip TIMESTAMP NOT NULL,
  date_return_trip TIMESTAMP NULL,
  city_from TEXT NOT NULL,
  city_to TEXT NOT NULL,
  description VARCHAR(500) NULL,
  price INT NOT NULL,
  sit_number INT NOT NULL,
  size_of_luggage VARCHAR(255) NOT NULL,
  id_cars INT NOT NULL,
  preference_id_preference INT NOT NULL,
  PRIMARY KEY (id_covoiturage),
  CONSTRAINT fk_covoiturage_cars1
    FOREIGN KEY (id_cars)
    REFERENCES cars (id_cars)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_covoiturage_preference1
    FOREIGN KEY (preference_id_preference)
    REFERENCES preference (id_preference)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE INDEX fk_covoiturage_cars1_idx ON covoiturage (id_cars ASC);

CREATE INDEX fk_covoiturage_preference1_idx ON covoiturage (preference_id_preference ASC);


-- -----------------------------------------------------
-- Table users_has_covoiturage
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users_has_covoiturage (
  id_users_has_covoiturage SERIAL NOT NULL,
  id_covoiturage INT NOT NULL,
  id_users INT NOT NULL,
  passagers boolean NULL,
  PRIMARY KEY (id_users_has_covoiturage),
  CONSTRAINT fk_users_has_covoiturage_covoiturage1
    FOREIGN KEY (id_covoiturage)
    REFERENCES covoiturage (id_covoiturage)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_users_has_covoiturage_users1
    FOREIGN KEY (id_users)
    REFERENCES users (id_users)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX fk_users_has_covoiturage_covoiturage1_idx ON users_has_covoiturage (id_covoiturage ASC);

CREATE INDEX fk_users_has_covoiturage_users1_idx ON users_has_covoiturage (id_users ASC);


-- -----------------------------------------------------
-- Table city_waypoints
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS city_waypoints (
  id_city_stop SERIAL NOT NULL,
  city_waypoints TEXT NULL,
  id_covoiturage INT NOT NULL,
  PRIMARY KEY (id_city_stop),
  CONSTRAINT fk_city_waypoints_covoiturage1
    FOREIGN KEY (id_covoiturage)
    REFERENCES covoiturage (id_covoiturage)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE INDEX fk_city_waypoints_covoiturage1_idx ON city_waypoints (id_covoiturage ASC);



-- -----------------------------------------------------
-- Table users_has_cars
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users_has_cars (
  id_users_has_carl SERIAL NOT NULL,
  id_users INT NOT NULL,
  id_cars INT NOT NULL,
  PRIMARY KEY (id_users_has_carl),
  CONSTRAINT fk_users_has_cars_users1
    FOREIGN KEY (id_users)
    REFERENCES users (id_users)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_users_has_cars_cars1
    FOREIGN KEY (id_cars)
    REFERENCES cars (id_cars)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX fk_users_has_cars_users1_idx ON users_has_cars (id_users ASC);

CREATE INDEX fk_users_has_cars_cars1_idx ON users_has_cars (id_cars ASC);


-- -----------------------------------------------------
-- Table opinion
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS opinion (
  id_opinion SERIAL NOT NULL,
  description VARCHAR(255) NULL,
  score INT NOT NULL,
  id_users_from INT NOT NULL,
  id_users_to INT NOT NULL,
  PRIMARY KEY (id_opinion),
  CONSTRAINT fk_opinion_users1
    FOREIGN KEY (id_users_from)
    REFERENCES users (id_users)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_opinion_users2
    FOREIGN KEY (id_users_to)
    REFERENCES users (id_users)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE INDEX fk_opinion_users1_idx ON opinion (id_users_from ASC);

CREATE INDEX fk_opinion_users2_idx ON opinion (id_users_to ASC);


INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,true,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,true,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,true,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,true,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,false,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,false,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,false,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,true,false,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,true,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,true,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,true,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,true,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,false,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,false,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,false,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (true,false,false,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,true,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,true,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,true,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,true,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,false,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,false,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,false,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,true,false,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,true,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,true,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,true,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,true,false,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,false,true,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,false,true,false);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,false,false,true);
INSERT INTO preference (smoking, animals, musics, detour,food) VALUES (false,false,false,false,false);
