CREATE TABLE users(
  id BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
  email VARCHAR(100)   NOT NULL UNIQUE,
  password VARCHAR(255)   NOT NULL,
  role_id  BIGINT REFERENCES roles(id)
);

INSERT INTO users(email,password,role_id)
VALUES ('polina@mail.ru','$2a$12$O8u7qZEY9C.Hop6Q7idPDOofFpxYKy5W2O6ijaQt5mNrzXRgdfvWK',
        (SELECT id FROM roles WHERE role = 'admin'));

INSERT INTO users(email,password,role_id)
VALUES ('Vova@mail.ru','$2a$12$O8u7qZEY9C.Hop6Q7idPDOofFpxYKy5W2O6ijaQt5mNrzXRgdfvWK',
        (SELECT id FROM roles WHERE role = 'user'));
