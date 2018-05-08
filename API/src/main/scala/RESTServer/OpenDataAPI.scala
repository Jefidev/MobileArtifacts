package RESTServer

import BusinessObjects._
import io.finch.syntax._
import io.finch._

/**
  * Created by jfink on 30/04/18.
  */
object OpenDataAPI {

  /**
    * Endpoint allowing user to retrieve list of all the neighbourhoods and their descriptions
    * on the url /open/neighbourhoods
    * @return list of [[BusinessObjects.OpenNeighbourhoods]]
    */
  val getNeighbourhoodInfo:Endpoint[List[OpenNeighbourhoods]] = get("open" :: "neighbourhoods"){
    Ok(Neighbourhoods.getAllInfo()).withHeader("Access-Control-Allow-Origin" -> "*")
  }


  /**
    * Endpoint allowing user to retrieve the neighbourhoods info based on coordinates on the url
    * /open/neighbourhoods/info
    * @note Should have json body in format {lat: float, longi: float}
    * @return [[BusinessObjects.NeighbourhoodInfo]]
    */
  val getInfoByCoordinate:Endpoint[NeighbourhoodInfo] = post("open"::"neighbourhoods"::"info"::Main.parseCoordinate){
    (c:Coordinate) =>
      Neighbourhoods.getNeighbourhood(c) match {
        case Some(n) => Ok(Neighbourhoods.getByName(n.getName)).withHeader("Access-Control-Allow-Origin" -> "*")
        case None => NotFound(new Exception("No neighbourhoods matching the coordinates")).withHeader("Access-Control-Allow-Origin" -> "*")
      }
  }


  /**
    * Endpoints allowing the user to retrieve complete information about all the neighbourhoods (name, description, sensors info)
    * at the url : /open/neighbourhoods/all
    * @return List of [[BusinessObjects.OpenNeighbourhoods]]
    */
  val getAllNeighbourhoodsInfo:Endpoint[List[OpenNeighbourhoods]] = get("open"::"neighbourhoods"::"all"){
    Ok(Neighbourhoods.getAllInfo()).withHeader("Access-Control-Allow-Origin" -> "*")
  }


  /**
    * Endpoint allowing the user to retrieve a list containing the name of all the neighbourhoods
    * /open/neighbourhoods/names
    * @return list of strings
    */
  val getNameNeighbourhood:Endpoint[JsonList] = get ("open"::"neighbourhoods"::"names"){
    Ok(JsonList(Neighbourhoods.getAll())).withHeader("Access-Control-Allow-Origin" -> "*")
  }


  /**
    * Retrieve the complete information about a neighbourhood based on his name at the url /open/neighbourhoods/#name
    * @note #name should be replaced by the name of the neighbourhood
    * @return [[BusinessObjects.NeighbourhoodInfo]]
    */
  val getInfoByName:Endpoint[NeighbourhoodInfo] = get("open"::"neighbourhoods"::path[String]){
    (name:String) =>
      Ok(Neighbourhoods.getByName(name)).withHeader("Access-Control-Allow-Origin" -> "*")
  }

}
