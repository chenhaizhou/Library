CREATE DATABASE  IF NOT EXISTS lib;
use lib;


drop table if exists borrows;
drop table if exists books;
drop table if exists images;
drop table if exists users;

CREATE TABLE images (
  image_id  INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  image_url VARCHAR(100)        NOT NULL
) ENGINE=InnoDB;

CREATE TABLE books (
  book_id        INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  book_name      varchar(100)               NOT NULL,
  author         VARCHAR(40)         NOT NULL,
  isbn           VARCHAR(40)         NOT NULL,
  publisher      VARCHAR(40)         NOT NULL,
  cover_image_id INT(11)             NOT NULL,
  introduction   VARCHAR(500),
  FULLTEXT(book_name, author, isbn, publisher),
  create_time datetime not null,
  FOREIGN KEY (cover_image_id) REFERENCES images (image_id)
) ENGINE=InnoDB;

alter table books add unique (isbn);

CREATE TABLE users (
  user_name VARCHAR(50) PRIMARY KEY NOT NULL,
  password  VARCHAR(50),
  name      VARCHAR(50)
) ENGINE=InnoDB;

alter table users add column is_built_admin bool default 1 not null;


CREATE TABLE borrows(
  id INT NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(45) NOT NULL,
  book_id INT NOT NULL,
  borrow_date DATETIME NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

  ALTER TABLE books
  ADD COLUMN borrowed_number INT(4) NOT NULL DEFAULT 0 AFTER create_time,
  ADD COLUMN total_number INT(4) NOT NULL DEFAULT 0 AFTER borrowed_number;

ALTER TABLE borrows
ADD FOREIGN KEY (book_id)
REFERENCES books(book_Id);

ALTER TABLE borrows
ADD FOREIGN KEY (user_name)
REFERENCES users(user_name);


ALTER TABLE borrows
ADD COLUMN return_date DATETIME NULL AFTER borrow_date,
ADD COLUMN is_returned INT(1) NOT NULL DEFAULT 0 AFTER return_date;