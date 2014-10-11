CREATE TABLE images (
  image_id  INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  image_url VARCHAR(100)        NOT NULL
);

CREATE TABLE books (
  book_id        INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  book_name      VARCHAR(100)        NOT NULL,
  author         VARCHAR(40)         NOT NULL,
  isbn           VARCHAR(40)         NOT NULL,
  publisher      VARCHAR(40)         NOT NULL,
  cover_image_id INT(11)             NOT NULL,
  introduction   VARCHAR(500),
  create_time DATETIME NOT NULL,
  FOREIGN KEY (cover_image_id) REFERENCES images (image_id)
);


--ALTER TABLE books
--ADD COLUMN create_time DATETIME NOT NULL
--AFTER introduction;

CREATE TABLE users (
  user_name VARCHAR(50) PRIMARY KEY NOT NULL,
  password  VARCHAR(50),
  name      VARCHAR(50)
);
