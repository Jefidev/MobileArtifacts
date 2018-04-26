package RESTServer

import BusinessObjects._
import io.finch.{Endpoint, Ok}
import io.finch.syntax._
import io.finch._
import querydsl.{NeighbourhoodData, UsersData}

/**
  * Created by jfink on 13/04/18.
  */
object Profile {

  val login:Endpoint[LoginMessage] = get("login" :: Main.authApp){ u:UsersData =>
    Ok(LoginMessage(true, u.getRfid))
  }

  val rfid:Endpoint[Message] = post("rfid" :: path[String] :: Main.authApp){ (r:String, u:UsersData) =>
        u.setRfid(r)
        User.updateUser(u)
        Ok(Message(u.getRfid))
  }

  val rfidGet:Endpoint[Message] = get("rfid"  :: Main.authApp){ u:UsersData =>
        Ok(Message(u.getRfid))
  }


  val getCurrentNeighbourhood:Endpoint[Message] = get("profile" :: "neighbourhood" :: Main.authApp :: Main.retrieveNeighbourhood){
    (m:UsersData, n:NeighbourhoodData) =>
      Ok(Message(n.getName))
  }

  val getAllNeighbourhood:Endpoint[List[String]] = get("neighbourhoods" :: Main.authApp){
    (m:UsersData) => Ok(Neighbourhoods.getAll())
  }

  val getNeighbourhoodByName:Endpoint[NeighbourhoodInfo] = get("neighbourhood" :: path[String] :: Main.authApp){
    (s:String, m:UsersData) => Ok(Neighbourhoods.getByName(s))
  }

  val bleh: Endpoint[Message] = get("profil"){
    Ok(Message("Hello"))
  }
}
