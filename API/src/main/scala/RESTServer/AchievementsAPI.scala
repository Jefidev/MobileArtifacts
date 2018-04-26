package RESTServer

import BusinessObjects._
import io.finch.{Endpoint, Error, NotFound, Ok, Unauthorized, path}
import io.finch.syntax._
import querydsl.UsersData

/**
  * Created by jfink on 17/04/18.
  */
object AchievementsAPI {

  val achievements:Endpoint[List[Achievement]] = get("achievements" :: Main.authApp){ u:UsersData =>
    Ok(Achievements.retrieveAchievements(u))
  }

  val achievement:Endpoint[AchievementDetail] = get("achievement" :: "detail" :: path[Int] :: Main.authApp){
    (s:Int, u:UsersData) => Ok(Achievements.retrieveAchievementDetail(u, s))
  }


  val validateEvent:Endpoint[MessageCode] = post("achievement" :: "read" :: path[Int] :: Main.parseSecret :: Main.authApp){
    (idEvent:Int, s:Secret, u:UsersData) =>
          Achievements.validateEvent(u, s.secret, idEvent)
          Ok(MessageCode("Success", 1))
  }.handle{
    case e:EventException => Ok(MessageCode(e.message, e.code))
    case e:Exception => Ok(MessageCode("System exception. Contact Jérôme", 42))
  }

}
