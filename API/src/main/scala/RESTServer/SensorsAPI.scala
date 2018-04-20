package RESTServer

import BusinessObjects.Message
import querydsl.NeighbourhoodData
import io.finch.{Endpoint, Ok}
import io.finch.syntax._
import io.finch._

/**
  * Created by jfink on 17/04/18.
  */
object SensorsAPI {

  def testEndpoint:Endpoint[Message] = get("orchestrator" :: "test" :: Main.authOrchestrator){ s:String =>
    println("Success raspberry")
    Ok(Message("Success"))
  }

  def updateValue:Endpoint[Message] = post("orchestrator" :: "value" :: Main.authOrchestrator){ s:String =>
    Ok(Message("yeah"))
  }

}
