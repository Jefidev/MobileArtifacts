package RESTServer

import BusinessObjects._
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.finch.syntax._
import io.circe.generic.auto._
import querydsl.{NeighbourhoodData, UsersData}


/**
  * Created by jfink on 09/03/18.
  */

//https://finagle.github.io/blog/2014/12/10/rest-apis-with-finch/

object Main extends App{

  //Parsing json value
  /**
    * All these endpoint should be
    */
  val parseSensor:Endpoint[SensorValue] = jsonBody[SensorValue]
  val parseSecret:Endpoint[Secret] = jsonBody[Secret]
  val parseMessage:Endpoint[Message] = jsonBody[Message]
  val parseSecretRFID:Endpoint[SecretRFID] = jsonBody[SecretRFID]
  val parseCoordinate:Endpoint[Coordinate] = jsonBody[Coordinate]

  val authApp:Endpoint[UsersData] = header("token").mapOutput(s =>
    User.createOrRetrieve(s) match {
      case Some(u) => Ok(u)
      case None => throw new Exception("Dead")
    }
  ).handle{
    case e : Error.NotPresent => Unauthorized(e)
    case e: Exception => Unauthorized(e)
  }

  val authOrchestrator:Endpoint[String] = header("token").mapOutput(s =>
    if(Orchestrators.verify(s))
      Ok(s)
    else
      Unauthorized(new Exception("Bad login"))
  ).handle{
    case e : Error.NotPresent => Unauthorized(e)
  }

  val retrieveNeighbourhood = jsonBody[Coordinate].mapOutput(c =>
    Neighbourhoods.getNeighbourhood(c) match {
      case Some(x) => Ok(x)
      case _ => NotFound(new Exception("bleh"))
    }
  ) handle{
    case e : Exception => NotFound(e)
  }

  val bleh: Endpoint[Message] = get("hello"){
    Ok(Message("Hello"))
  }


  val api = (Profile.login :+: Profile.rfid :+: Profile.rfidGet :+: Profile.bleh :+: Profile.getCurrentNeighbourhood :+:
    Profile.getAllNeighbourhood :+: Profile.getNeighbourhoodByName :+:
    bleh :+: SensorsAPI.testEndpoint :+: SensorsAPI.updateValue
    :+: AchievementsAPI.achievement :+: AchievementsAPI.achievements :+: AchievementsAPI.validateEvent
    :+: AchievementsAPI.validateEventRFID :+: AchievementsAPI.validateWriteRFID :+: AchievementsAPI.achievementLog
    :+: OpenDataAPI.getNeighbourhoodInfo :+: OpenDataAPI.getAllNeighbourhoodsInfo
    :+: OpenDataAPI.getNameNeighbourhood :+: OpenDataAPI.getInfoByName :+: OpenDataAPI.getInfoByCoordinate)
    .toServiceAs[Application.Json]

  Await.ready(Http.server.serve(":8081", api))
}
