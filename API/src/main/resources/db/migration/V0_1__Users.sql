create table Users (
  idUsers varchar(255) primary key,
  name varchar(255),
  rfid VARCHAR(255) NOT NULL DEFAULT '0'
)
ENGINE = InnoDB;