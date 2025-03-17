--changeset piton:10
ALTER TABLE user
    ADD COLUMN username VARCHAR(128),
    ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123',
    ADD COLUMN role VARCHAR(32);

--changeset piton:11
ALTER TABLE security_user_aud
    ADD COLUMN username VARCHAR(128),
    ADD COLUMN password VARCHAR(128),
    ADD COLUMN role VARCHAR(32);