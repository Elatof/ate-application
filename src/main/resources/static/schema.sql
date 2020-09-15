DROP TABLE IF EXISTS item;

CREATE TABLE item
(
    id        SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL
);

INSERT INTO item(name)
VALUES ('SWIMMING'),
       ('YOGA'),
       ('RUNNING');