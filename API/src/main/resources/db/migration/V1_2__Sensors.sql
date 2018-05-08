create table Sensors (
  id int primary key,
  name varchar(255),
  type int,
  orchestrator varchar(255),
  lastValue float,
  lastUpdate TIMESTAMP,
  FOREIGN KEY (`orchestrator`)
  REFERENCES Orchestrators(token)
)
ENGINE = InnoDB;


insert into Sensors values (1, 'temperature', 1, '269544d3-99e7-4c52-b1c4-660f4a59df32', -999, UTC_TIMESTAMP());
insert into Sensors values (2, 'light', 2, '269544d3-99e7-4c52-b1c4-660f4a59df32', -999, UTC_TIMESTAMP());
insert into Sensors values (5, 'gaz', 5, '269544d3-99e7-4c52-b1c4-660f4a59df32', -999, UTC_TIMESTAMP());
insert into Sensors values (6, 'temperature', 1, '269544d3-99e7-4c52-b1c4-660f4a59df32', -999, UTC_TIMESTAMP());
insert into Sensors values (7, 'humidity', 6, '269544d3-99e7-4c52-b1c4-660f4a59df32', -999, UTC_TIMESTAMP());

insert into Sensors values (8, 'temperature', 1, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 25, UTC_TIMESTAMP());
insert into Sensors values (9, 'light', 2, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 35, UTC_TIMESTAMP());
insert into Sensors values (10, 'gaz', 5, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 100, UTC_TIMESTAMP());
insert into Sensors values (11, 'temperature', 1, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 26,UTC_TIMESTAMP());
insert into Sensors values (12, 'humidity', 6, 'cd30d08d-981c-4d22-94fb-eb2860f2b21f', 12, UTC_TIMESTAMP());

insert into Sensors values (13, 'temperature', 1, '819b9fc6-363e-4cb3-b8cf-3f4453497254', 25, UTC_TIMESTAMP());
insert into Sensors values (14, 'light', 2, '819b9fc6-363e-4cb3-b8cf-3f4453497254', 50, UTC_TIMESTAMP());
insert into Sensors values (15, 'gaz', 5, '819b9fc6-363e-4cb3-b8cf-3f4453497254', 85, UTC_TIMESTAMP());
insert into Sensors values (16, 'temperature', 1, '819b9fc6-363e-4cb3-b8cf-3f4453497254', 24,UTC_TIMESTAMP());
insert into Sensors values (17, 'humidity', 6, '819b9fc6-363e-4cb3-b8cf-3f4453497254', 17, UTC_TIMESTAMP());