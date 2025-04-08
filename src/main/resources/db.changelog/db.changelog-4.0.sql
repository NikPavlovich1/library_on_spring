--changeset piton:10
CREATE TABLE admins (
                        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(32) NOT NULL,
                        user_id BIGINT,
                        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);