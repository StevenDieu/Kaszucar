CREATE TABLE IF NOT EXISTS address (
  id_address SERIAL NOT NULL,
  address VARCHAR(1024) NULL,
  complement_address VARCHAR(1024) NULL,
  postal_code smallint NULL,
  country VARCHAR(255) NULL,
  PRIMARY KEY (id_address));

CREATE TABLE IF NOT EXISTS users (
  id_users SERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  phone_number smallint NULL,
  email_adress VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  ip_address VARCHAR(15) NOT NULL,
  id_address INT NOT NULL,
  genre VARCHAR(3) NOT NULL,
  year_of_birth smallint NOT NULL,
  description VARCHAR(500) NULL,
  url_picture VARCHAR(1024) NULL,
  PRIMARY KEY (id_users));


CREATE UNIQUE INDEX email_adress_UNIQUE ON users (email_adress ASC);

CREATE INDEX fk_users_address1_idx ON users (id_address ASC);

CREATE UNIQUE INDEX phone_number_UNIQUE ON users (phone_number ASC);


CREATE TABLE IF NOT EXISTS cars (
  id_cars INT NOT NULL,
  brand VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  comfort VARCHAR(45) NULL,
  color VARCHAR(45) NULL,
  PRIMARY KEY (id_cars));

CREATE TABLE IF NOT EXISTS preference (
  id_preference SERIAL NOT NULL,
  smoking boolean NULL,
  animals boolean NULL,
  musics boolean NULL,
  detour boolean NULL,
  food boolean NULL,
  PRIMARY KEY (id_preference));

CREATE TABLE IF NOT EXISTS covoiturage (
  id_covoiturage SERIAL NOT NULL,
  date_first_trip DATE NOT NULL,
  date_return_trip DATE NULL,
  city_from TEXT NOT NULL,
  city_to TEXT NOT NULL,
  description VARCHAR(500) NULL,
  price smallint NOT NULL,
  id_cars INT NULL,
  sit_number smallint NOT NULL,
  size_of_luggage VARCHAR(255) NOT NULL,
  id_preference INT NOT NULL,
  PRIMARY KEY (id_covoiturage));


CREATE INDEX fk_covoiturage_cars1_idx ON covoiturage (id_cars ASC);

CREATE INDEX fk_covoiturage_preference1_idx ON covoiturage (id_preference ASC);

CREATE TABLE IF NOT EXISTS users_has_covoiturage (
  id_users_has_covoiturage SERIAL NOT NULL,
  users_id_users INT NOT NULL,
  covoiturage_id_covoiturage INT NOT NULL,
  PRIMARY KEY (id_users_has_covoiturage));


CREATE INDEX fk_users_has_covoiturage_covoiturage1_idx ON users_has_covoiturage (covoiturage_id_covoiturage ASC);

CREATE INDEX fk_users_has_covoiturage_users1_idx ON users_has_covoiturage (users_id_users ASC);

CREATE TABLE IF NOT EXISTS city_waypoints (
  id_city_stop SERIAL NOT NULL,
  id_covoiturage INT NOT NULL,
  city_waypoints TEXT NULL,
  order_waypoints smallint NULL,
  PRIMARY KEY (id_city_stop));


CREATE INDEX fk_city_stop_covoiturage1_idx ON city_waypoints (id_covoiturage ASC);


CREATE TABLE IF NOT EXISTS users_has_cars (
  id_users_has_carl SERIAL NOT NULL,
  id_cars INT NOT NULL,
  id_users INT NOT NULL,
  PRIMARY KEY (id_users_has_carl));


CREATE INDEX fk_users_has_cars_cars1_idx ON users_has_cars (id_cars ASC);

CREATE INDEX fk_users_has_cars_users1_idx ON users_has_cars (id_users ASC);



CREATE TABLE IF NOT EXISTS opinion (
  id_opinion SERIAL NOT NULL,
  id_users_from INT NOT NULL,
  id_users_to INT NOT NULL,
  description VARCHAR(255) NULL,
  score smallint NOT NULL,
  PRIMARY KEY (id_opinion));


CREATE INDEX fk_opinion_users1_idx ON opinion (id_users_from ASC);

CREATE INDEX fk_opinion_users2_idx ON opinion (id_users_to ASC);
