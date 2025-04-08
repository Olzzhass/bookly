--changeset olzhas:1
CREATE TABLE IF NOT EXISTS tables.author (
    id bigserial primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    bio text,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
)