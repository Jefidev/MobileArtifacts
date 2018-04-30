create table Orchestrators (
  token varchar(255) primary key,
  name varchar(255),
  neighbourhood int,
  FOREIGN KEY (`neighbourhood`)
  REFERENCES Neighbourhood(id)
)
ENGINE = InnoDB;

insert into Orchestrators values ('269544d3-99e7-4c52-b1c4-660f4a59df32', 'RaspberryCentre', 1);
insert into Orchestrators values ('cd30d08d-981c-4d22-94fb-eb2860f2b21f', 'RaspberryFaculté', 2);