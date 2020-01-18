CREATE TABLE author
(
    id           BIGINT (20)  NOT NULL AUTO_INCREMENT UNIQUE,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE post
(
    id                  BIGINT (20)     NOT NULL AUTO_INCREMENT UNIQUE,
    body                VARCHAR(255)    NOT NULL,
    date                DATETIME        NOT NULL,
    title               VARCHAR(255)    NOT NULL,
    author_id           BIGINT (20)     NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE post
    ADD CONSTRAINT post_author_id_author_id
        FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE;

