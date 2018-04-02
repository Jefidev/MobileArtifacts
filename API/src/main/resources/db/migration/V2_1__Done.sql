create table Done (
  achievementId integer,
  userId varchar(255),
  earningDate DATETIME,
  FOREIGN KEY (achievementId) REFERENCES Achievements(id),
  FOREIGN KEY (userId) REFERENCES Users(idUsers),
  PRIMARY KEY (achievementId, userId)
)
ENGINE = InnoDB;