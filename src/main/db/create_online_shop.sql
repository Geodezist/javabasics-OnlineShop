drop user online_shop;
create user online_shop with createdb password 'online_shop';
create database online_shop with owner online_shop;
select current_database() as a, current_schema() as b;
CREATE SCHEMA online_shop AUTHORIZATION online_shop;

CREATE TABLE online_shop.product
(
  id serial PRIMARY KEY NOT NULL,
  name varchar(250) NOT NULL,
  picture_path varchar(1000),
  price real NOT NULL,
  add_date timestamp NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX product_id_uindex ON online_shop.product (id);
CREATE UNIQUE INDEX product_name_uindex ON online_shop.product (name);

CREATE TABLE online_shop."user"
(
    id serial PRIMARY KEY NOT NULL,
    login varchar(100) NOT NULL,
    password_hash varchar(1000) NOT NULL,
    salt varchar(1000) NOT NULL,
    iterations int NOT NULL,
    role varchar(50) NOT NULL
);
CREATE UNIQUE INDEX user_id_uindex ON online_shop."user" (id);
CREATE UNIQUE INDEX user_login_uindex ON online_shop."user" (login);

