--changeset olzhas:3
CREATE TABLE IF NOT EXISTS tables.book_author (
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES tables.book(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES tables.author(id) ON DELETE CASCADE
)