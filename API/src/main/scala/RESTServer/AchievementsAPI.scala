package RESTServer

import BusinessObjects._
import io.finch.{Endpoint, Error, NotFound, Ok, Unauthorized, path}
import io.finch.syntax._
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


  val validateEvent:Endpoint[Message] = post("achievement" :: "read" :: path[Int] :: Main.parseSecret :: Main.authApp){
    (idEvent:Int, s:Secret, m:Option[UsersData]) =>
      m match {
        case Some(u) => {
          Achievements.validateEvent(u, s.secret, idEvent)
          Ok(Message("Success"))
        }
        case None => Ok(Message("Failure: Not authenticated"))
      }
  }.handle{
    case e:Error => Ok(Message(e.getMessage))
  }

}
