package BusinessObjects

import repositories.LogsRepository
import scala.collection.JavaConverters._

/**
  * Created by jfink on 30/04/18.
  */
object LogsUtils {
  val repo = new LogsRepository()

  def getLogsMessage(idAchievement:Int, idUser:String):List[Message] ={
    for(i<-repo.getLogsByAchievements(idUser,idAchievement).asScala.toList)
      yield Message(i.getMessage)
  }


  def getUniqueMessage(idAchievement:Int, idUser:String):List[Message] = {
    for(i <- repo.getUniqueMessages(idUser, idAchievement).asScala.toList)
      yield Message(i)
  }

  def deleteByAchievements(idAchievement:Int, idUser:String):Unit = {
    repo.deleteLogsByAchievements(idUser,idAchievement)
  }

  def addLog(idAchievement:Int, idUser:String, message:String): Unit ={
    repo.addLogs(idUser, idAchievement, message)
  }
}
