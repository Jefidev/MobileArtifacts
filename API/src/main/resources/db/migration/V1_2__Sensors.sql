create table Sensors (
  id int primary key,
  name varchar(255),
  type int,
  orchestrator varchar(255),
  lastValue float,
  FOREIGN KEY (`orchestrator`)
  REFERENCES `mobile`.`Orchestrators` (`token`)
)
ENGINE = InnoDB;


insert into Sensors values (1, 'temperature', 1, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (2, 'light', 2, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (3, 'rfid write', 3, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (4, 'rfid read', 4, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (5, 'gaz', 5, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (6, 'temperature', 1, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (7, 'humidity', 6, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);