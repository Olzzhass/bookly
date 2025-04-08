--changeset olzhas:2
CREATE TABLE IF NOT EXISTS tables.book (
    id bigserial primary key,
    name varchar(255) not null unique,
    description text,
    isbn varchar(255) unique not null,
    published_date timestamp,
    language varchar(50),
    average_rating float,
    review_count int default 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
)