--changeset piton:9
ALTER TABLE Books
    ADD COLUMN available BOOLEAN;
UPDATE Books
SET available = TRUE;
ALTER TABLE Books
    ALTER COLUMN available SET NOT NULL;