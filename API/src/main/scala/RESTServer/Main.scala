package RESTServer

import Main.api
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch.{Application, Endpoint, Ok}
import io.finch.syntax.get

/**
  * Created by jfink on 09/03/18.
  */

//https://finagle.github.io/blog/2014/12/10/rest-apis-with-finch/

object Main extends App{

  case class Payload(payload:String)

  val bleh: Endpoint[Payload] = get("hello"){
    Ok(Payload("Hello"))
  }

  Await.ready(Http.server.serve(":8081", bleh.toService))
}