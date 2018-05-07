package BusinessObjects

import java.sql.Timestamp
import java.time.{Duration, Instant}
import java.util.Date

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
      yield SensorInfo(i.getType, i.getName, i.getLastValue, isSensorDead(i.getLastUpdate))
  }

  def getByName(s:String):NeighbourhoodInfo = {
    val n:NeighbourhoodData = repo.getOne(s)
    NeighbourhoodInfo(n.getName, n.getDescription, getSensorsInfo(n.getId))
  }

  private def isSensorDead(timestamp: Timestamp):Boolean = {
    val dateRef = Instant.now().minus(Duration.ofMinutes(4L))

    println(dateRef.toEpochMilli)
    println(timestamp.getTime)

    timestamp.toInstant.plus(Duration.ofHours(2L)).isBefore(dateRef)
  }

}
