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
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(32) NOT NULL UNIQUE,
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
    state         VARCHAR(32)  NOT NULL,
    common_price   INT          NOT NULL,
    image_url     VARCHAR(255),

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

INSERT INTO brand(name, image_url)
VALUES ('jack foxskin', 'https://res.cloudinary.com/elatof/image/upload/v1612185145/ate-project/brand/1-id_dt7etp.jpg'),
       ('butterfly', 'https://res.cloudinary.com/elatof/image/upload/v1612185145/ate-project/brand/2-id_uxz742.jpg'),
       ('wender', 'https://res.cloudinary.com/elatof/image/upload/v1612185145/ate-project/brand/3-id_ifbovd.jpg');

INSERT INTO type(name)
VALUES ('сноуборд'),
       ('гірські лижі'),
       ('палатки'),
       ('взуття'),
       ('спальник'),
       ('аксесуари');

INSERT INTO address(country, city, street, build_number)
VALUES ('Україна', 'Львів', 'Величковського', 6),
       ('Україна', 'Львів', 'Виговського', 20);

INSERT INTO department(name, address_id)
VALUES ('Відділення №001', 1),
       ('Відділення №002', 2);

INSERT INTO item(name, description, price, type_id, brand_id, department_id, state, common_price, image_url)
VALUES ('Лижі шанс', 'description', 90, 2, 2, 1, 'задовільний', 5200,
        'https://res.cloudinary.com/elatof/image/upload/v1612185145/ate-project/item/1-id_iddgvn.jpg'),
       ('Сноуборд 228', 'description', 150, 1, 1, 1, 'добрий', 2600,
        'https://res.cloudinary.com/elatof/image/upload/v1612185145/ate-project/item/2-id_l2nfsr.jpg'),
       ('Палатка X', 'description', 150, 3, 2, 2, 'ідеальний', 7000,
        'https://res.cloudinary.com/elatof/image/upload/v1615995267/ate-project/item/3-id_zs7saz.jpg');


INSERT INTO employee(first_name, second_name, is_admin, password, department_id)
VALUES ('Maksym', 'Korbiak', 1, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC', 1),
       ('Victor', 'Pikyluk', 1, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC', 2),
       ('Roman', 'Kutylo', 2, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC', null),
       ('Andrii', 'Klymchuk', 3, '$2a$10$sOl0xuyeqvtH/5SZTXwLLeJtB/nMulcJKoLPuoKqFevCgUTOIK9SC', null);
-- password = 'admin' for these users

INSERT INTO customer(name, surname, phone, email)
VALUES ('Maksym', 'Korbiak', '+380980265122', 'maksik125@gmail.com');

INSERT INTO item_order(start_date, end_date, employee_id, item_id, customer_id)
VALUES ('2.5.2021', '2.7.2021', 1, 1, 1),
       ('3.1.2021', '3.5.2021', 1, 2, 1),
       ('7.25.2019', '8.1.2019', 2, 3, 1);




