CREATE TABLE task(
  id INT NOT NULL,
  message VARCHAR(1000),
  priority INT,
   PRIMARY KEY (id)
);

insert into task (id,message,priority) values
(1, 'Clean the house', 1),
(2, 'Read a book', 2),
(3, 'Fix the code', 3);


commit;