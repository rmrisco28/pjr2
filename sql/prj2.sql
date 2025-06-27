USE prj2;
# 게시판 테이블
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

SELECT *
FROM board;


INSERT INTO board
    (title, content, author)
SELECT title, content, author
FROM board;

SELECT *
FROM board
ORDER BY id DESC;


DELETE
FROM board
WHERE content = "";

# 멤버 테이블
CREATE TABLE member
(
    id                VARCHAR(255) NOT NULL,
    password          VARCHAR(255) NOT NULL,
    name              VARCHAR(255) NOT NULL,
    nickname          VARCHAR(255) NOT NULL,
    registration_date DATETIME     NOT NULL DEFAULt CURRENT_TIMESTAMP(),
    CONSTRAINT pk_member PRIMARY KEY (id)
);



SELECT *
FROM member;

DROP TABLE member;

CREATE TABLE user
(
    id                VARCHAR(100) NOT NULL,
    password          VARCHAR(100) NOT NULL,
    name              VARCHAR(100) NOT NULL,
    nickname          VARCHAR(100) NOT NULL,
    registration_date datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

DROP TABLE user;

SELECT *
FROM user;
