create table Achievements (
  id integer primary key auto_increment,
  name varchar(255),
  description text,
  points int
)
ENGINE = InnoDB;