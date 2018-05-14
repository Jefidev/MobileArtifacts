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
insert into Events values (default, 0, 'L\’église st loup', 4, '935bcbd2099c692ea646ac662bd7771e', null, 'Scan');
insert into Events values (default, 0, 'L\’ancienne abbaye Notre-Dame du Vivier', 4, 'd294ab1155ee50a7611ea3913c5ad31b', null, 'Scan');
insert into Events values (default, 0, 'L\’église Notre-Dame ', 4, '7f20be940206aa8231d5c53be461e48e', null, 'Scan');
insert into Events values (default, 0, 'L\’église Saint-Jean Baptiste', 4, 'd5789645b8fcd373b14a5d27144e9143', null, 'Scan');
insert into Events values (default, 0, 'Le calvaire de Frizet à Vedrin', 4, '9b0e356cd0589d2763ca3e2c79a00cfc', null, 'Scan');
insert into Events values (default, 0, 'L\’église Notre-Dame de Gelbressée', 4, 'de0d258a6dc0437b1b156192cff588be', null, 'Scan');


# Moyen âge
insert into Events values (default, 0, 'Immeuble, rue des Brasseurs, 5', 5, '7985bc6dd6b763c73e4f51965269c543', null, 'Scan');
insert into Events values (default, 0, 'Ancien donjon et la maison seigneuriale d’Anhaive', 5, '768a205e6929951e77b2b8c5a66ca78b', null, 'Scan');
insert into Events values (default, 0, 'La ferme-château et la chapelle Sainte-Apolline de Wartet', 5, '132674b05d9d6acdba599aac61c16dbf', null, 'Scan');





