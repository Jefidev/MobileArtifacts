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


  val validateEventRFID:Endpoint[MessageCode] = post("orchestrator"::"achievement"::"read"::path[Int]:: Main.parseSecretRFID ::Main.authOrchestrator){
    (id:Int, sec:SecretRFID , s:String) =>
      print("Requête read reçue")
      User.getUserByRFID(sec.idRfid) match {
        case Some(u) => {
          print("Requête read reçue  et rfid valide")
          Achievements.validateEvent(u, sec.secret, id)
          Ok(MessageCode("Success", 1))
        }
        case None => Ok(MessageCode("RFID card is not linked to a user", 5))
      }
  }.handle{
    case e:EventException => Ok(MessageCode(e.message, e.code))
    case e:Exception => Ok(MessageCode("System exception. Contact Jérôme avec le message : " + e.getMessage, 42))
  }

  val validateWriteRFID:Endpoint[MessageCode] = post("orchestrator"::"achievement"::"write"::path[Int]:: Main.parseSecretRFID ::Main.authOrchestrator){
    (id:Int, sec:SecretRFID , s:String) =>
      print("Requete write reçue")
      User.getUserByRFID(sec.idRfid) match {
        case Some(u) => {
          print("Requete write reçue et rfid valide")
          Achievements.validateEvent(u, sec.secret, id)
          Ok(MessageCode("Success", 1))
        }
        case None => Ok(MessageCode("RFID card is not linked to a user", 5))
      }
  }.handle{
    case e:EventException => Ok(MessageCode(e.message, e.code))
    case e:Exception => {
      e.printStackTrace
      Ok(MessageCode("System exception. Contact Jérôme avec le message : " + e.getMessage, 42))
    }
  }

}
