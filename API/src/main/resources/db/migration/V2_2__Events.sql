create table Events (
  id integer primary key auto_increment,
  type int,
  description text,
  achievement INTEGER,
  FOREIGN KEY (achievement) REFERENCES Achievements(id)
)
  ENGINE = InnoDB;