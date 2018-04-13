package BusinessObjects

/**
  * Created by jfink on 03/04/18.
  */

case class Event(eType: Int, id: Int, key: Int)
case class Message(payload:String)
case class Coordinate(lat:Float, long:Float)
case class LoginMessage(success:Boolean, rfid:String)