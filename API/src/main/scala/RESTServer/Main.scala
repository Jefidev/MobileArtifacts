package RESTServer

import BusinessObjects.{Coordinate, Message, Orchestrators, User}
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.finch.syntax._
import io.circe.generic.auto._
import querydsl.UsersData


/**
  * Created by jfink on 09/03/18.
  */

//https://finagle.github.io/blog/2014/12/10/rest-apis-with-finch/

object Main extends App{

  val authApp:Endpoint[Option[UsersData]] = header("token").mapOutput(s =>
    Ok(User.createOrRetrieve(s))
  ).handle{
    case e : Error.NotPresent => Unauthorized(e)
  }

  val authOrchestrator:Endpoint[Boolean] = header("token").mapOutput(s =>
    if(Orchestrators.verify(s))
      Ok(true)
    else
      Unauthorized(new Exception("Bad login"))
  ).handle{
    case e : Error.NotPresent => Unauthorized(e)
  }

  val bleh: Endpoint[Message] = get("hello"){
    Ok(Message("Hello"))
  }

  val loginOrch:Endpoint[Message] = get("sensors" :: "login" :: authOrchestrator){m:Boolean =>
    Ok(Message("Success"))
  }


  //ENDPOINT FOR NEIGHBOORHOOD
  val retrieveCoordinateBody = jsonBody[Coordinate]
  val getNeighbourhood:Endpoint[Message] = post("neighbourhood" :: "name" :: retrieveCoordinateBody){
    c:Coordinate =>
      println(c.lat)
      println(c.long)
      Ok(Message("echo"))
  }


  val api = (Profile.login :+: Profile.rfid :+: Profile.rfidGet :+:
    bleh :+: loginOrch :+: getNeighbourhood).toServiceAs[Application.Json]

  Await.ready(Http.server.serve(":8081", api))
}
