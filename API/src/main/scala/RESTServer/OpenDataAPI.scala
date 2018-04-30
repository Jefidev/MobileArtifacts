package RESTServer

import BusinessObjects.{Coordinate, NeighbourhoodInfo, Neighbourhoods, OpenNeighbourhoods}
import io.finch.Endpoint
import io.finch.syntax._
import io.finch._

/**
  * Created by jfink on 30/04/18.
  */
object OpenDataAPI {

  val getNeighbourhoodInfo:Endpoint[List[OpenNeighbourhoods]] = get("open" :: "neighbourhoods"){
    Ok(Neighbourhoods.getAllInfo())
  }

  val getInfoByCoordinate:Endpoint[NeighbourhoodInfo] = get("open"::"neighbourhoods"::"info"::Main.parseCoordinate){
    (c:Coordinate) =>
      Neighbourhoods.getNeighbourhood(c) match {
        case Some(n) => Ok(Neighbourhoods.getByName(n.getName))
        case None => NotFound(new Exception("No neighbourhoods matching the coordinates"))
      }
  }

  val getAllNeighbourhoodsInfo:Endpoint[List[OpenNeighbourhoods]] = get("open"::"neighbourhoods"::"all"){
    Ok(Neighbourhoods.getAllInfo())
  }

}
