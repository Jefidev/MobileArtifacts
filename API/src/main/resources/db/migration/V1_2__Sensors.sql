create table Sensors (
  id int primary key,
  name varchar(255),
  type int,
  orchestrator varchar(255),
  lastValue float,
  FOREIGN KEY (`orchestrator`)
  REFERENCES Orchestrators(token)
)
ENGINE = InnoDB;


insert into Sensors values (1, 'temperature', 1, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (2, 'light', 2, '269544d3-99e7-4c52-b1c4-660f4a59df32', 65);
insert into Sensors values (3, 'rfid write', 3, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (4, 'rfid read', 4, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (5, 'gaz', 5, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (6, 'temperature', 1, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);
insert into Sensors values (7, 'humidity', 6, '269544d3-99e7-4c52-b1c4-660f4a59df32', 0);


insert into Sensors values (8, 'temperature', 1, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 25);
insert into Sensors values (9, 'light', 2, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 85);
insert into Sensors values (10, 'gaz', 5, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 100);
insert into Sensors values (11, 'temperature', 1, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 26);
insert into Sensors values (12, 'humidity', 6, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 12);