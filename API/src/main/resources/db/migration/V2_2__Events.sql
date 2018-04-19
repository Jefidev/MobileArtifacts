create table Events (
  id integer primary key auto_increment,
  type int, #1 read value, 2 write value
  description text,
  achievement INTEGER,
  val text,
  preRequise INTEGER,
  target INTEGER,
  FOREIGN KEY (achievement) REFERENCES Achievements(id)
)
  ENGINE = InnoDB;
