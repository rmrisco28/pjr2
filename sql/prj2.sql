USE prj2;

CREATE TABLE board
(
    id      INT AUTO_INCREMENT NOT NULL,
    title   VARCHAR(255)       NOT NULL,
    content VARCHAR(255)       NOT NULL,
    author  VARCHAR(255)       NOT NULL,
    created datetime           NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_board PRIMARY KEY (id)
);

DESC board;
ALTER TABLE board
    MODIFY id BIGINT AUTO_INCREMENT;

DROP TABLE board;

INSERT INTO board
    (title, content, author)
SELECT title, content, author
FROM board;