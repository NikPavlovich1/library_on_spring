--liquibase formatted sql

--changeset piton:4
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP;

--changeset piton:5
ALTER TABLE users
    ADD COLUMN modified_at TIMESTAMP;

--changeset piton:6
ALTER TABLE users
    ADD COLUMN created_by VARCHAR(32);

--changeset piton:7
ALTER TABLE users
    ADD COLUMN modified_by VARCHAR(32);

--changeset piton:8
ALTER TABLE transactions
    ALTER COLUMN return_date SET NOT NULL;
