package RESTServer

import BusinessObjects.{Achievement, Achievements, LoginMessage}
import io.finch.{Endpoint, Ok}
import io.finch.syntax.get
import querydsl.UsersData

/**
  * Created by jfink on 17/04/18.
  */
object AchievementsAPI {

  val achievements:Endpoint[List[Achievement]] = get("achievements" :: Main.authApp){ m:Option[UsersData] =>
    m match {
      case Some(u) => Ok(Achievements.retrieveAchievements(u))
      case _ => Ok(List())
    }
  }

}
