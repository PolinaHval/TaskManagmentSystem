CREATE TABLE roles(
    id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    role VARCHAR NOT NULL
);

INSERT INTO roles(role)
VALUES ('admin');

INSERT INTO roles(role)
VALUES ('user');