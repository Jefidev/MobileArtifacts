create table Sensors (
  id int primary key,
  name varchar(255),
  type int,
  orchestrator int,
  lasValur float,
  FOREIGN KEY (`orchestrator`)
  REFERENCES `mobile`.`Orchestrators` (`token`)
)
ENGINE = InnoDB;