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

  def testEndpoint:Endpoint[Message] = get("orchestrator" :: "test" :: Main.authOrchestrator){ s:Boolean =>
    s match {
      case true => Ok(Message("Success"))
      case _ => Ok(Message("Failure"))
    }
  }

}
