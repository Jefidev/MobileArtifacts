package BusinessObjects

import querydsl.NeighbourhoodData
import repositories.NeighbourhoodRepository
import scala.collection.JavaConverters._

/**
  * Created by jfink on 03/04/18.
  */
object Neighbourhoods {

  val repo = new NeighbourhoodRepository()

  def getNeighbourhood(c:Coordinate):Option[NeighbourhoodData] = {
    Option(repo.getNeighbourhood(c))
  }

  def getAllInfo():List[OpenNeighbourhoods] = {
    for(i <- repo.getAll.asScala.toList)
      yield OpenNeighbourhoods(i.getName, repo.getAllCoordinates(i.getId).asScala.toList, getSensorsInfo(i.getId), Coordinate(i.getHubLat, i.getHubLong))
  }

  def getAll():List[String] = {
    for( i <- repo.getAll.asScala.toList) yield i.getName
  }

  def getSensorsInfo(id:Int):List[SensorInfo] = {
    for(i <- repo.getSensorsByNeighbourhood(id).asScala.toList)
      yield SensorInfo(i.getType, i.getName, i.getLastValue)
  }

  def getByName(s:String):NeighbourhoodInfo = {
    val n:NeighbourhoodData = repo.getOne(s)
    NeighbourhoodInfo(n.getName, n.getDescription, getSensorsInfo(n.getId))
  }

}
