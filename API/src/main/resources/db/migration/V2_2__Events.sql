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

insert into Events values (default, 0, 'Trouvez et scannez le signe de St Aubain', 1, 'KYT-1-789', null, 'Scan');

insert into Events values (default, 1, 'Enregistrement au point de départ', 2, 'KYT-2-900', null, 'Enregistrement du départ');
insert into Events values (default, 1, 'Enregistrement au point de destination', 2, 'KYT-3-900', 2, 'Validation du trajet');

insert into Events values (default, 0, 'Scan d\'un tag au soleil couchant (16-18h)', 3, 'KYT-4-247', null, 'Scan');
insert into Contexts values (4, 2, 300, 100);

# Eglises
insert into Events values (default, 0, 'L\’église st loup', 4, 'de399644560364ab43f3a7bedea8331d', null, 'Scan');
insert into Events values (default, 0, 'L\’ancienne abbaye Notre-Dame du Vivier', 4, 'd1a9ddbef75820a135390ec83804c631', null, 'Scan');
insert into Events values (default, 0, 'L\’église Notre-Dame ', 4, '683221f4b07f6fc57ebfa8ad2553377b', null, 'Scan');
insert into Events values (default, 0, 'L\’église Saint-Jean Baptiste', 4, 'e60819b7e26a755d1235ef03bd3d8028', null, 'Scan');
insert into Events values (default, 0, 'Le calvaire de Frizet à Vedrin', 4, '2e7e167381dd45ec31f5034ca0acd232', null, 'Scan');
insert into Events values (default, 0, 'L\’église Notre-Dame de Gelbressée', 4, '7119a940a48429ebada505fe3749ad30', null, 'Scan');


# Moyen âge
insert into Events values (default, 0, 'Immeuble, rue des Brasseurs, 5', 5, 'c183b0fb2c1ee21c60245a23f70f8e3c', null, 'Scan');
insert into Events values (default, 0, 'Ancien donjon et la maison seigneuriale d’Anhaive', 5, '0fd7470772174225efca8340cd41565f', null, 'Scan');
insert into Events values (default, 0, 'La ferme-château et la chapelle Sainte-Apolline de Wartet', 5, 'c80b54f521918048decc695743573ffb', null, 'Scan');





