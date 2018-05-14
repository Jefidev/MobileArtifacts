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

  def checkSecret(secret:String, event:EventsData, u:UsersData):Boolean = {

    //Test secret KYT-[0-9]+-[0-9]+
    if(event.getSecret == secret)
      true
    else
      throw EventException("Le code scanné est invalide !", 2, u.getIdUsers, event)

  }

  def checkContext(event:EventsData, u:UsersData):Boolean = {
    val contexts:List[EventContext] = repo.getContext(event.getId).asScala.toList
    val contextBroken:Int = contexts.count(x => !(x.min <= x.lastVal && x.lastVal < x.max))

    //TODO Bad time slot

    if(contextBroken == 0 )
      true
    else
      throw EventException("Le conditions pour scanner ce tag ne sont pas respectées !", 3, u.getIdUsers, event)
  }


  def checkDependency(u:UsersData, event:EventsData):Boolean = {
    val preReq:Int = event.getPreRequise

    preReq match {
      case 0 => true
      case b => {
        if(repo.alreadyDone(u, b))
          true
        else
          throw EventException("Vous avez sauté une étape dans la réalisation de l'achievement !", 4, u.getIdUsers, event)
      }
    }
  }

  def retrieveEvent(id:Int):EventsData = {
    val e:Option[EventsData] = Option(repo.getEvent(id))

    e match{
      case Some(e) => e
      case None => throw EventNotFoundException("Aucune correspondance pour le tag scanné !", 6)
    }
  }

  def retrieveEventBySecret(secret:String):EventsData = {
    val e:Option[EventsData] = Option(repo.getEventBySecret(secret))

    e match{
      case Some(e) => e
      case None => throw EventNotFoundException("Aucune correspondance pour le tag scanné !", 6)
    }
  }

  def validatePatrimoineEvent(u:UsersData, secret:String):EventsData = {
    println("patrimoine :")
    println(secret)
    val event:EventsData = retrieveEventBySecret(secret)
    validateEvent(u, secret, event.getId)

  }


  def validateEvent(u:UsersData, secret:String, id:Int):EventsData  = {
    val event:EventsData = retrieveEvent(id)

    if(!alreadyDone(u, id)){
      checkSecret(secret, event, u)
      checkContext(event, u)
      checkDependency(u, event)

      //Mark as done
      repo.eventDone(id, u.getIdUsers)
      event
    }
    else
      throw EventException("Cette étape a déjà été réalisée !", 5, u.getIdUsers, event)
  }

}
