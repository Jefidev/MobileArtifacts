create table Orchestrators (
  token int primary key,
  name varchar(255),
  neighbourhood int,
  FOREIGN KEY (`neighbourhood`)
  REFERENCES `mobile`.`Neighbourhood` (`id`)
)
ENGINE = InnoDB;