create table Achievements (
  id integer primary key auto_increment,
  name varchar(255),
  description text,
  points int
)
ENGINE = InnoDB;

insert into Achievements VALUES (DEFAULT, 'St Aubain', 'Au coeur de Namur', 100);
insert into Achievements VALUES (DEFAULT, 'Route merveilleuse (RFID)', 'Vous avez survécu à l''ascension ce la citadelle', 100);
insert into Achievements VALUES (DEFAULT, 'Coucher de soleil citadelle', 'Du haut de la citadelle, 600 ans vous contemplent', 100);

insert into Achievements VALUES (DEFAULT , 'Edifices religieux', 'Tour des édifices religieux de Namur', 400);
insert into Achievements VALUES (DEFAULT , 'Namur au Moyen-âge', 'Vestiges Namurois', 200)