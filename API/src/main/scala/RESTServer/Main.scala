package RESTServer

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

  val bleh: Endpoint[Message] = get("hello"){
    Ok(Message("Hello"))
  }

  Await.ready(Http.server.serve(":8081", bleh.toServiceAs[Application.Json]))
}
