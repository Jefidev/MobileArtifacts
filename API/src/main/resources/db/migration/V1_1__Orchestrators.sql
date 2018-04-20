create table Orchestrators (
  token varchar(255) primary key,
  name varchar(255),
  neighbourhood int,
  FOREIGN KEY (`neighbourhood`)
  REFERENCES `mobile`.`Neighbourhood` (`id`)
)
ENGINE = InnoDB;

insert into Orchestrators values ('269544d3-99e7-4c52-b1c4-660f4a59df32', 'Raspberry', 1)