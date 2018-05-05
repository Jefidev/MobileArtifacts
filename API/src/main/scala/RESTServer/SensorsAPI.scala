package RESTServer

import BusinessObjects.{Message, Orchestrators, SensorValue}
import io.finch.{Endpoint, Ok}
import io.finch.syntax._
import io.finch._
import querydsl.SensorsData
import repositories.SensorsRepository

/**
  * Created by jfink on 17/04/18.
  */
object SensorsAPI {

  def testEndpoint:Endpoint[Message] = get("orchestrator" :: "test" :: Main.authOrchestrator){ s:String =>
    Ok(Message("Success"))
  }

  def updateValue:Endpoint[Message] = post("orchestrator" :: "value" :: Main.authOrchestrator :: Main.parseSensor){
    (s:String, value:SensorValue)=> {
      Orchestrators.setValue(s, value.id, value.lastVal)
      Ok(Message("Success"))
    }
  }

}
