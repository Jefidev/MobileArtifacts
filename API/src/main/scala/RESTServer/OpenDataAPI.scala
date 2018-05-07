package RESTServer

import BusinessObjects._
import io.finch.syntax._
import io.finch._

/**
  * Created by jfink on 30/04/18.
  */
object OpenDataAPI {

  val getNeighbourhoodInfo:Endpoint[List[OpenNeighbourhoods]] = get("open" :: "neighbourhoods"){
    Ok(Neighbourhoods.getAllInfo()).withHeader("Access-Control-Allow-Origin" -> "*")
  }

  val getInfoByCoordinate:Endpoint[NeighbourhoodInfo] = get("open"::"neighbourhoods"::"info"::Main.parseCoordinate){
    (c:Coordinate) =>
      Neighbourhoods.getNeighbourhood(c) match {
        case Some(n) => Ok(Neighbourhoods.getByName(n.getName)).withHeader("Access-Control-Allow-Origin" -> "*")
        case None => NotFound(new Exception("No neighbourhoods matching the coordinates")).withHeader("Access-Control-Allow-Origin" -> "*")
      }
  }

  val getAllNeighbourhoodsInfo:Endpoint[List[OpenNeighbourhoods]] = get("open"::"neighbourhoods"::"all"){
    Ok(Neighbourhoods.getAllInfo()).withHeader("Access-Control-Allow-Origin" -> "*")
  }

  val getNameNeighbourhood:Endpoint[JsonList] = get ("open"::"neighbourhoods"::"names"){
    Ok(JsonList(Neighbourhoods.getAll())).withHeader("Access-Control-Allow-Origin" -> "*")
  }

  val getInfoByName:Endpoint[NeighbourhoodInfo] = get("open"::"neighbourhoods"::path[String]){
    (name:String) =>
      Ok(Neighbourhoods.getByName(name)).withHeader("Access-Control-Allow-Origin" -> "*")
  }

}
