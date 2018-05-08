package BusinessObjects

import java.sql.Timestamp
import java.time.{Duration, Instant}

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
    val brut = for(i <- repo.getSensorsByNeighbourhood(id).asScala.toList)
      yield SensorInfo(i.getType, i.getName, i.getLastValue, isSensorDead(i.getLastUpdate, i.getLastValue))

    //Aggregation des données de sensor
    brut.groupBy(_.sType).map(b => aggregation(b._2)).toList
  }

  private def aggregation(list: List[SensorInfo]):SensorInfo = {
    val sensorOk = list.filter(s => !s.dead)

    if(sensorOk.isEmpty)
      list.head
    else{
      val avg = sensorOk.map(_.value).sum / sensorOk.length
      SensorInfo(sensorOk.head.sType, sensorOk.head.name, avg, sensorOk.head.dead)
    }
  }

  def getByName(s:String):NeighbourhoodInfo = {
    val n:NeighbourhoodData = repo.getOne(s)
    NeighbourhoodInfo(n.getName, n.getDescription, getSensorsInfo(n.getId))
  }

  private def isSensorDead(timestamp: Timestamp, value: Float):Boolean = {
    val dateRef = Instant.now().minus(Duration.ofMinutes(4L))
    timestamp.toInstant.isBefore(dateRef) || value < -900
  }

}
