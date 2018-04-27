create table Events (
  id integer primary key auto_increment,
  type int, #0 read value, 1 write value
  description text,
  achievement INTEGER,
  secret text,
  preRequise INTEGER,
  name varchar(255),
  FOREIGN KEY (achievement) REFERENCES Achievements(id),
  FOREIGN KEY (preRequise) REFERENCES Events(id)
)
ENGINE = InnoDB;


create table Contexts(
  idEvents integer,
  idSensor integer,
  maxVal float,
  minVal float,
  FOREIGN KEY (idEvents) REFERENCES Events(id),
  FOREIGN KEY (idSensor) REFERENCES Sensors(id),
  PRIMARY KEY (idEvents, idSensor)
)
ENGINE = InnoDB;

insert into Events values (default, 0, 'Trouvez et scannez le signe du Beffroi', 1, 'KYT-1-789', null, 'Scan');

insert into Events values (default, 1, 'Enregistrement au point de départ', 2, 'KYT-2-900', null, 'Enregistrement du départ');
insert into Events values (default, 1, 'Enregistrement au point de destination', 2, 'KYT-3-900', 2, 'Validation du trajet');

insert into Events values (default, 0, 'Scan du tag entre 16h00 et 18h00', 3, 'KYT-4-247', null, 'Scan');
insert into Contexts values (4, 2, 100, 50);


