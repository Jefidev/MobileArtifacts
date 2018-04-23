create table Neighbourhood (
  id int primary key auto_increment,
  name varchar(255),
  description text
)
ENGINE = InnoDB;


create table Coordinates (
  id int primary key auto_increment,
  latitude float,
  longitude float
)
ENGINE = InnoDB;


create table lien (
  neighbourhoodId int,
  coordinateID int,
  ord int,
  FOREIGN KEY (neighbourhoodId) REFERENCES Neighbourhood(id),
  FOREIGN KEY (coordinateID) REFERENCES Coordinates(id),
  PRIMARY KEY (neighbourhoodId, coordinateID)
)
ENGINE = InnoDB;

insert into Neighbourhood VALUES (DEFAULT, 'Centre', 'Sa place, ses églises, ses commerces');
insert into Neighbourhood VALUES (DEFAULT, 'Citadelle', 'Du haut de la citadelle, quarante siecles vous contemplent');

insert into Coordinates VALUES (DEFAULT, 50.462848, 4.857915);
insert into Coordinates VALUES (DEFAULT, 50.4616841, 4.8627377);
insert into Coordinates VALUES (DEFAULT, 50.4615612, 4.8653126);
insert into Coordinates VALUES (DEFAULT, 50.461906, 4.867786);
insert into Coordinates VALUES (DEFAULT, 50.464351, 4.866734);
insert into Coordinates VALUES (DEFAULT, 50.466113, 4.860941);

insert into Coordinates VALUES (DEFAULT, 50.4681540, 4.8573970);
insert into Coordinates VALUES (DEFAULT, 50.4668020, 4.8539210);

insert into lien VALUES (1, 1, 1);
insert into lien VALUES (1, 2, 2);
insert into lien VALUES (1, 3, 3);
insert into lien VALUES (1, 4, 4);
insert into lien VALUES (1, 5, 5);
insert into lien VALUES (1, 6, 6);

insert into lien VALUES (2, 1, 1);
insert into lien VALUES (2, 6, 2);
insert into lien VALUES (2, 7, 3);
insert into lien VALUES (2, 8, 4);

