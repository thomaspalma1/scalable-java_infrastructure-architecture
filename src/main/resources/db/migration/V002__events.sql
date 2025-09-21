CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    city VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    street VARCHAR(100) NOT NULL,
    district VARCHAR(100) NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    number VARCHAR(20),
    complement VARCHAR(100)
);

CREATE TABLE purchases (
    id BIGSERIAL PRIMARY KEY,
    purchase_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    CONSTRAINT fk_purchases_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    description VARCHAR(100) NOT NULL,
    price DECIMAL(7,2) NOT NULL,
    code VARCHAR(255) NOT NULL UNIQUE,
    purchase_id BIGINT,
    type VARCHAR(100),
    version INT NOT NULL,
    CONSTRAINT fk_tickets_event FOREIGN KEY(event_id) REFERENCES events(id) ON DELETE CASCADE,
    CONSTRAINT fk_tickets_purchase FOREIGN KEY(purchase_id) REFERENCES purchases(id) ON DELETE SET NULL
);
