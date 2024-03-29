package RESTServer

import BusinessObjects._
import io.finch.{Endpoint, Error, NotFound, Ok, Unauthorized, path}
import io.finch.syntax._
import querydsl.{EventsData, LogsData, UsersData}

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


  val validatePatrimoineEvent:Endpoint[MessageCode] = post("achievement" :: "read" :: Main.parseSecret :: Main.authApp){
    (s:Secret, u:UsersData) =>
      println(s.secret)
      val e:EventsData = Achievements.validatePatrimoineEvent(u, s.secret)
      Ok(MessageCode("Success", e.getAchievement, e.getDescription, 1))
  }.handle{
    case e:EventException => {
      LogsUtils.addLog(e.event.getAchievement, e.idUser, e.message)
      Ok(MessageCode(e.message, e.event.getAchievement,e.event.getDescription , e.code))
    }
    case e:EventNotFoundException => Ok(MessageCode(e.message, 0,"" , e.code))
    case e:Exception => Ok(MessageCode("System exception. Contact Jérôme" + e.getMessage, 0, "", 42))
  }


  val validateEvent:Endpoint[MessageCode] = post("achievement" :: "read" :: path[Int] :: Main.parseSecret :: Main.authApp){
    (idEvent:Int, s:Secret, u:UsersData) =>
          val e:EventsData = Achievements.validateEvent(u, s.secret, idEvent)
          Ok(MessageCode("Success", e.getAchievement, e.getDescription, 1))
  }.handle{
    case e:EventException => {
      LogsUtils.addLog(e.event.getAchievement, e.idUser, e.message)
      Ok(MessageCode(e.message, e.event.getAchievement,e.event.getDescription , e.code))
    }
    case e:EventNotFoundException => Ok(MessageCode(e.message, 0,"" , e.code))
    case e:Exception => Ok(MessageCode("System exception. Contact Jérôme" + e.getMessage, 0, "", 42))
  }


  val validateEventRFID:Endpoint[MessageCode] = post("orchestrator"::"achievement"::"read"::path[Int]:: Main.parseSecretRFID ::Main.authOrchestrator){
    (id:Int, sec:SecretRFID , s:String) =>
      User.getUserByRFID(sec.idRfid) match {
        case Some(u) => {
          val e:EventsData = Achievements.validateEvent(u, sec.secret, id)
          Ok(MessageCode("Success", e.getAchievement, e.getDescription, 1))
        }
        case None => Ok(MessageCode("RFID card is not linked to a user", 0, "", 5))
      }
  }.handle{
    case e:EventException => {
      LogsUtils.addLog(e.event.getAchievement, e.idUser, e.message)
      Ok(MessageCode(e.message, e.event.getAchievement, e.event.getDescription ,e.code))
    }
    case e:EventNotFoundException => Ok(MessageCode(e.message, 0,"" , e.code))
    case e:Exception => Ok(MessageCode("System exception. Contact Jérôme avec le message : " + e.getMessage, 0, "", 42))
  }

  val validateWriteRFID:Endpoint[MessageCode] = post("orchestrator"::"achievement"::"write"::path[Int]:: Main.parseSecretRFID ::Main.authOrchestrator){
    (id:Int, sec:SecretRFID , s:String) =>
      User.getUserByRFID(sec.idRfid) match {
        case Some(u) => {
          val e:EventsData =Achievements.validateEvent(u, sec.secret, id)
          Ok(MessageCode("Success", e.getAchievement, e.getDescription, 1))
        }
        case None => Ok(MessageCode("RFID card is not linked to a user", 0, "", 18))
      }
  }.handle{
    case e:EventException => {
      LogsUtils.addLog(e.event.getAchievement, e.idUser, e.message)
      Ok(MessageCode(e.message, e.event.getAchievement, e.event.getDescription, e.code))
    }
    case e:EventNotFoundException => Ok(MessageCode(e.message, 0,"" , e.code))
    case e:Exception => {
      Ok(MessageCode("System exception. Contact Jérôme avec le message : " + e.getMessage, 0, "", 42))
    }
  }


  val achievementLog:Endpoint[List[Message]] = get("achievement"::"logs"::path[Int]::Main.authApp){
    (idAchievement:Int, u:UsersData) =>
      val tmp:List[Message] = LogsUtils.getUniqueMessage(idAchievement, u.getIdUsers)
      LogsUtils.deleteByAchievements(idAchievement, u.getIdUsers)
      Ok(tmp)
  }

}
