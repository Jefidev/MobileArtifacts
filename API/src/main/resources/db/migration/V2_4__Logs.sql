create table Logs (
  id integer primary key auto_increment,
  message text,
  idUser varchar(255),
  idAchievement integer,
  FOREIGN KEY (idUser) REFERENCES Users(idUsers),
  FOREIGN KEY (idAchievement) REFERENCES Achievements(id)
)
