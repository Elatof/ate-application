DROP TABLE IF EXISTS item_order;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS address;


CREATE TABLE address
(
    id           SERIAL PRIMARY KEY,
    country      VARCHAR(32) NOT NULL,
    city         VARCHAR(32) NOT NULL,
    street       VARCHAR(32) NOT NULL,
    build_number INT         NOT NULL,

    UNIQUE (country, city, street, build_number)
);

CREATE TABLE department
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(32) NOT NULL,
    address_id INT         NOT NULL UNIQUE,

    CONSTRAINT fk_address
        FOREIGN KEY (address_id)
            REFERENCES address (id)
);

CREATE TABLE employee
(
    id            SERIAL PRIMARY KEY,
    first_name    VARCHAR(32)  NOT NULL,
    second_name   VARCHAR(32)  NOT NULL,
    is_admin      INT          NOT NULL,
    password      VARCHAR(155) NOT NULL,
    department_id INT,

    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
            REFERENCES department (id),

    UNIQUE (first_name, second_name)
);

CREATE TABLE type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE brand
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE,
    image_url VARCHAR(255)
);

CREATE TABLE item
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(32)  NOT NULL,
    description   VARCHAR(255) NOT NULL,
    price         INT          NOT NULL,
    type_id       INT          NOT NULL,
    brand_id      INT          NOT NULL,
    department_id INT          NOT NULL,

    CONSTRAINT fk_department_item
        FOREIGN KEY (department_id)
            REFERENCES department (id),

    CONSTRAINT fk_type
        FOREIGN KEY (type_id)
            REFERENCES type (id),

    CONSTRAINT fk_brand
        FOREIGN KEY (brand_id)
            REFERENCES brand (id)
);

CREATE TABLE customer
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    phone   VARCHAR(32) NOT NULL,
    email   VARCHAR(32) NOT NULL
);

CREATE TABLE item_order
(
    id          SERIAL PRIMARY KEY,
    start_date  DATE NOT NULL,
    end_date    DATE NOT NULL,
    employee_id INT  NOT NULL,
    item_id     INT  NOT NULL,
    customer_id INT  NOT NULL,

    CONSTRAINT fk_employee
        FOREIGN KEY (employee_id)
            REFERENCES employee (id),

    CONSTRAINT fk_item
        FOREIGN KEY (item_id)
            REFERENCES item (id),

    CONSTRAINT fk_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
);

INSERT INTO brand(name)
VALUES ('jack foxskin'),
       ('butterfly'),
       ('wender');

INSERT INTO type(name)
VALUES ('сноуборд'),
       ('гірські лижі'),
       ('палатки'),
       ('взуття'),
       ('спальник'),
       ('аксесуари');

INSERT INTO address(country, city, street, build_number)
VALUES ('Україна', 'Львів', 'Величковського', 6);

INSERT INTO department(name, address_id)
VALUES ('Відділення №001', 1);

INSERT INTO item(name, description, price, type_id, brand_id, department_id)
VALUES ('Сноуборд 228', 'description', 90, 1, 2, 1),
       ('Лижі шанс', 'description', 60, 2, 1, 1);

INSERT INTO employee(first_name, second_name, is_admin, password)
VALUES ('Maksym', 'Korbiak', 1, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC'),
       ('Roman', 'Kutylo', 2, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC'),
       ('Andrii', 'Klymchuk', 1, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC');
-- password = 'admin' for these users

