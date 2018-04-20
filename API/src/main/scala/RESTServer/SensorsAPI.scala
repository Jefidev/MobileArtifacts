package RESTServer

import BusinessObjects.{Coordinate, Message, SensorValue}
import io.finch.{Endpoint, Ok}
import io.finch.syntax._
import io.finch._
import repositories.SensorsRepository
import io.circe.generic.auto._

/**
  * Created by jfink on 17/04/18.
  */
object SensorsAPI {

  val repo = new SensorsRepository()

  def testEndpoint:Endpoint[Message] = get("orchestrator" :: "test" :: Main.authOrchestrator){ s:String =>
    println("Success raspberry")
    Ok(Message("Success"))
  }

  def updateValue:Endpoint[Message] = post("orchestrator" :: "value" :: Main.authOrchestrator :: Main.parseSensor){
    (s:String, value:SensorValue)=> {
      repo.setValue(s, value.id, value.lastVal)
      Ok(Message("Success"))
    }
  }

}
