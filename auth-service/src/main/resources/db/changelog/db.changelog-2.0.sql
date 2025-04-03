--changeset olzhas:2
CREATE TABLE tables.refresh_tokens (
    id bigserial primary key,
    user_id bigint not null references tables.auth_users(id) on delete cascade,
    token text not null unique,
    expires_at timestamp not null,
    created_at timestamp default current_timestamp
)