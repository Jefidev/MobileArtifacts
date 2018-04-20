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
  val parseSensor:Endpoint[SensorValue] = jsonBody[SensorValue]

  val authApp:Endpoint[Option[UsersData]] = header("token").mapOutput(s =>
    Ok(User.createOrRetrieve(s))
  ).handle{
    case e : Error.NotPresent => Unauthorized(e)
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
    AchievementsAPI.achievements :+: bleh :+: SensorsAPI.testEndpoint).toServiceAs[Application.Json]

  Await.ready(Http.server.serve(":8081", api))
}
