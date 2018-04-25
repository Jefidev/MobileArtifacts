package BusinessObjects

import com.sun.java.accessibility.util.EventID
import querydsl.{EventsData, QEvents, UsersData}
import repositories.AchievementsRepository

import collection.JavaConverters._


/**
  * Created by jfink on 13/04/18.
  */
object Achievements {

  val repo:AchievementsRepository = new AchievementsRepository

  def retrieveAchievements(u:UsersData):List[Achievement] = {
    repo.getAllAchievements(u).asScala.toList
  }

  def retrieveAchievementDetail(u:UsersData, id:Int):AchievementDetail = {
    val ach:Achievement = repo.getAchievement(u, id)
    val events:List[EventsDetail] = repo.getDetails(u.getIdUsers, id).asScala.toList
    AchievementDetail(ach, events)
  }

  def alreadyDone(u: UsersData, eventID:Int):Boolean = {
    repo.alreadyDone(u, eventID)
  }

  def checkSecret(secret:String, eventID:Int):Boolean = {
    val event:EventsData = repo.getEvent(eventID)
    if(event.getSecret == secret)
      true
    else
      throw new Error("Bad secret")

  }

  def checkContext(eventId:Int):Boolean = {
    val contexts:List[EventContext] = repo.getContext(eventId).asScala.toList
    val contextBroken:Int = contexts.count(x => !(x.min <= x.lastVal && x.lastVal < x.max))

    if(contextBroken == 0 )
      true
    else
      throw new Error("Contexte not respected")
  }

  def validateEvent(u:UsersData, secret:String, id:Int) = {
    if(alreadyDone(u, id)){
      checkSecret(secret, id)
      checkContext(id)
      //Mark as done
      repo.eventDone(id, u.getIdUsers)
    }
    else
      Unit
  }
}
