create table Accomplished (
  idEvent integer,
  idUser varchar(255),
  earningDate DATETIME,
  FOREIGN KEY (idUser) REFERENCES Users(idUsers),
  FOREIGN KEY (idEvent) REFERENCES Events(id),
  PRIMARY KEY (idEvent, idUser)
)
  ENGINE = InnoDB;