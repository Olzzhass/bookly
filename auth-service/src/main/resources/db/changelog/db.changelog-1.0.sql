--changeset olzhas:1
CREATE TABLE IF NOT EXISTS tables.auth_users (
    id BIGSERIAL PRIMARY KEY,
    username varchar(255) unique not null,
    email varchar(255) unique not null,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
)