package RESTServer

import BusinessObjects.{LoginMessage, Message}
import io.finch.{Endpoint, Ok}
import io.finch.syntax._
import io.finch._
import querydsl.{NeighbourhoodData, UsersData}
import BusinessObjects.User

/**
  * Created by jfink on 13/04/18.
  */
object Profile {

  val login:Endpoint[LoginMessage] = get("login" :: Main.authApp){ m:Option[UsersData] =>
    println("Login endpoint")
    m match {
      case Some(u) => Ok(LoginMessage(true, u.getRfid))
      case _ => Ok(LoginMessage(false, "0"))
    }
  }

  val rfid:Endpoint[Message] = post("rfid" :: path[String] :: Main.authApp){ (r:String, m:Option[UsersData]) =>
    m match {
      case Some(u) => {
        u.setRfid(r)
        User.updateUser(u)
        Ok(Message(u.getRfid))
      }
      case _ => Ok(Message("Failure"))
    }
  }

  val rfidGet:Endpoint[Message] = get("rfid"  :: Main.authApp){ m:Option[UsersData] =>
    m match {
      case Some(u) => {
        Ok(Message(u.getRfid))
      }
      case _ => Ok(Message("Failure"))
    }
  }


  val getCurrentNeighbourhood:Endpoint[Message] = post("profile" :: "neighbourhood" :: Main.authApp :: Main.retrieveNeighbourhood){
    (m:Option[UsersData], n:NeighbourhoodData) =>
      m match {
        case Some(u) => Ok(Message(n.getName))
        case _ => Ok(Message("failure"))
      }
  }

  val bleh: Endpoint[Message] = get("profil"){
    Ok(Message("Hello"))
  }
}
