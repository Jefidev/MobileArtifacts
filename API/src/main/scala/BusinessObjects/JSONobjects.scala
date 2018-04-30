package BusinessObjects

/**
  * Created by jfink on 03/04/18.
  */

case class Event(eType: Int, id: Int, key: Int)
case class Message(payload:String)
case class Secret(secret:String)
case class MessageCode(payload:String, code:Int)
case class SecretRFID(idRfid:String, secret:String)

case class Coordinate(lat:Float, longi:Float)
case class LoginMessage(success:Boolean, rfid:String)

case class Achievement(id:Int, name:String, desc:String, done:Boolean, points:Int)
case class EventsDetail(id:Int, name:String, description:String, done:Boolean)
case class AchievementDetail(achievement:Achievement, events:List[EventsDetail])
case class EventContext(min:Float, max:Float, lastVal:Float, sensor:String)

case class SensorValue(id:Int, lastVal:Float)
case class SensorInfo(sType:Int, name:String, value:Float)
case class NeighbourhoodInfo(name:String, description:String, sensors:List[SensorInfo])

//Données open data
case class OpenNeighbourhoods(name:String, points:List[Coordinate])


//Exceptions
case class EventException(message:String, code:Int) extends Exception