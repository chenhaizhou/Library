CREATE TABLE images (
    image_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    image_url varchar(100) not null
);

CREATE TABLE books (
    book_id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    book_name VARCHAR(100) NOT NULL,
    author VARCHAR(40) NOT NULL,
    isbn VARCHAR(40) NOT NULL,
    publisher VARCHAR(40) NOT NULL,
    cover_image_id INT(11) NOT NULL,
    introduction VARCHAR(500),
    FOREIGN KEY (cover_image_id) REFERENCES images (image_id)
);
