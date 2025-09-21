CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    ssn VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE access_roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY(role_id) REFERENCES access_roles(id) ON DELETE CASCADE
);

INSERT INTO access_roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO access_roles (id, name) VALUES (2, 'ROLE_BUYER');

INSERT INTO users (id, name, ssn, email, birth_date, password) VALUES
(1, 'Administrator', '00000000000', 'admin@email.com', '1990-01-01', '$2a$10$NRPNQpth64z1VKCrOdtZ/Otpk5zHsbaVXdXYPJazgsaH/QBvE3WPy');

INSERT INTO users (id, name, ssn, email, birth_date, password) VALUES
(2, 'Buyer', '11111111111', 'buyer@email.com', '1980-10-10', '$2a$10$f.lj9PV2eC2H3LLZzsYciu.Gkg.zTu9mIa05DfCQMCvmzw47WolXu');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
