create table Neighbourhood (
  id int primary key auto_increment,
  name varchar(255),
  description text,
  hub_lat float,
  hub_long float
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

insert into Neighbourhood VALUES (DEFAULT, 'St_Aubain', 'Sa place, ses églises, ses commerces', 50.464361, 4.860737);
insert into Neighbourhood VALUES (DEFAULT, 'Citadelle', 'C\'est beau mais c\'est haut', 50.461305, 4.868951);
insert into Neighbourhood VALUES (DEFAULT, 'Jambe', 'La ou y\'a le ciné', 50.455693, 4.873420);

#st aubain
insert into Coordinates VALUES (DEFAULT, 50.4667340, 4.8544550);
insert into Coordinates VALUES (DEFAULT, 50.4628310, 4.8575610);
insert into Coordinates VALUES (DEFAULT, 50.4617380, 4.8629250);
insert into Coordinates VALUES (DEFAULT, 50.4652450, 4.8635640);
insert into Coordinates VALUES (DEFAULT, 50.4679090, 4.8576090);

#citadelle
insert into Coordinates VALUES (DEFAULT, 50.4566840, 4.8637350);
insert into Coordinates VALUES (DEFAULT, 50.4617150, 4.8710790);
insert into Coordinates VALUES (DEFAULT, 50.4620430, 4.8699200);
insert into Coordinates VALUES (DEFAULT, 50.4617380, 4.8629250);

#jambe
insert into Coordinates VALUES (DEFAULT, 50.465499, 4.883207);
insert into Coordinates VALUES (DEFAULT, 50.456362, 4.885151);
insert into Coordinates VALUES (DEFAULT, 50.449346, 4.868725);
insert into Coordinates VALUES (DEFAULT, 50.453882, 4.863146);


#st aubain
insert into lien VALUES (1, 1, 1);
insert into lien VALUES (1, 2, 2);
insert into lien VALUES (1, 3, 3);
insert into lien VALUES (1, 4, 4);
insert into lien VALUES (1, 5, 5);

#citadelle
insert into lien VALUES (2, 2, 1);
insert into lien VALUES (2, 6, 2);
insert into lien VALUES (2, 7, 3);
insert into lien VALUES (2, 8, 4);
insert into lien VALUES (2, 9, 5);

#jambe
insert into lien VALUES (3, 6, 1);
insert into lien VALUES (3, 7, 2);
insert into lien VALUES (3, 10, 3);
insert into lien VALUES (3, 11, 4);
insert into lien VALUES (3, 12, 5);
insert into lien VALUES (3, 13, 6);

