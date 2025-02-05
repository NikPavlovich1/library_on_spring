--liquibase formatted sql

--changeset piton:1
CREATE TABLE Users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    lastname VARCHAR(255) NOT NULL,
    surname VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    book_id BIGINT,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Books(id)
);

--changeset piton:2
CREATE TABLE Books
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL CHECK (category IN ('B', 'Ps', 'O', 'L', 'Pr', 'NP')),
    category_order BIGINT       NOT NULL,
    composite_key  VARCHAR(10) GENERATED ALWAYS AS (category || '-' || category_order) STORED,
    UNIQUE (category, category_order)
);

--changeset piton:3
CREATE TABLE Transactions (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    issue_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES Users(id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Books(id),
    CONSTRAINT unique_book_issue UNIQUE (book_id, return_date)
);