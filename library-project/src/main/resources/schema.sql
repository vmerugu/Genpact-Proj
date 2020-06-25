DROP TABLE IF EXISTS library_details;
  
CREATE TABLE library_details (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  location VARCHAR(250) NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
);

DROP TABLE IF EXISTS book_details;

CREATE TABLE book_details (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL,
  subject VARCHAR(250) NOT NULL,
  library_id INT NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
);

alter table book_details add constraint FKp0t3i5i5n8gvw6dgf425v42p3 foreign key (library_id) references library_details(id);