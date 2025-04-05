--changeset olzhas:1
CREATE TABLE IF NOT EXISTS tables.user_profile (
    id BIGSERIAL PRIMARY KEY,
    username varchar(255) not null unique ,
    first_name varchar(255),
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
)