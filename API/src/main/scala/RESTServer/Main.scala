package RESTServer

import BusinessObjects.{Login, User}
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.finch.syntax._
import io.circe.generic.auto._


/**
  * Created by jfink on 09/03/18.
  */

//https://finagle.github.io/blog/2014/12/10/rest-apis-with-finch/

object Main extends App{

  case class Message(payload:String)

  val auth:Endpoint[Message] = header("token").mapOutput(s =>
    if(User.verify(s)) Ok(Message("Login successful"))
    else Ok(Message("Invalid toket"))
  ).handle{
    case e : Error.NotPresent => Unauthorized(e)
  }

  val bleh: Endpoint[Message] = get("hello"){
    Ok(Message("Hello"))
  }

  val login:Endpoint[Message] = get("login" :: auth){m:Message =>
    Ok(m)
  }


  val api = (login :+: bleh).toServiceAs[Application.Json]

  Await.ready(Http.server.serve(":8081", api))
}
