package BusinessObjects

/**
  * Created by jfink on 03/04/18.
  */

case class Event(eType: Int, id: Int, key: Int)
case class Message(payload:String)
case class Coordinate(lat:Float, longi:Float)
case class LoginMessage(success:Boolean, rfid:String)
case class Achievement(id:Int, name:String, desc:String, done:Boolean, points:Int)
case class SensorValue(id:Int, lastVal:Float)