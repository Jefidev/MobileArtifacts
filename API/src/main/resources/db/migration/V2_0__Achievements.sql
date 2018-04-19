create table Achievements (
  id integer primary key auto_increment,
  name varchar(255),
  description text,
  points int
)
ENGINE = InnoDB;

insert into Achievements VALUES (DEFAULT, 'Beffroi de Namur', 'Du haut de ce Beffroi, 600 ans vous contemples', 100);
insert into Achievements VALUES (DEFAULT, 'Route merveilleuse', 'Vous avez survécut à l''assention ce la citadelle', 100);
insert into Achievements VALUES (DEFAULT, 'Coucher de soleil citadelle', 'Du haut de ce Beffroi, 600 ans vous contemples', 100);