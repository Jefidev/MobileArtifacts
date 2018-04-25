package RESTServer

import BusinessObjects.{Achievement, AchievementDetail, Achievements, LoginMessage}
import io.finch.{Endpoint, Error, NotFound, Ok, Unauthorized, path}
import io.finch.syntax.get
import querydsl.UsersData

/**
  * Created by jfink on 17/04/18.
  */
object AchievementsAPI {

  val achievements:Endpoint[List[Achievement]] = get("achievements" :: Main.authApp){ m:Option[UsersData] =>
    m match {
      case Some(u) => Ok(Achievements.retrieveAchievements(u))
      case _ => Ok(List[Achievement]())
    }
  }

  val achievement:Endpoint[AchievementDetail] = get("achievement" :: "detail" :: path[Int] :: Main.authApp){
    (s:Int, m:Option[UsersData]) =>
      m match {
        case Some(u) => Ok(Achievements.retrieveAchievementDetail(u, s))
        case _ => NotFound(new Exception("bleh"))
      }
  }

}
