package RESTServer

import BusinessObjects.{Message, Orchestrators, User}
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

  val login:Endpoint[Message] = get("login" :: authApp){m:Option[UsersData] =>
    m match {
      case Some(u) => Ok(Message("Success"))
      case _ => Ok(Message("Failure"))
    }
  }


  val loginOrch:Endpoint[Message] = get("sensors" :: "login" :: authOrchestrator){m:Boolean =>
    Ok(Message("Success"))
  }


  val api = (login :+: bleh :+: loginOrch).toServiceAs[Application.Json]

  Await.ready(Http.server.serve(":8081", api))
}
