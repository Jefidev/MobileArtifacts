package BusinessObjects

import querydsl.NeighbourhoodData
import repositories.NeighbourhoodRepository
import scala.collection.JavaConverters._

/**
  * Created by jfink on 03/04/18.
  */
object Neighbourhoods {

  val repo = new NeighbourhoodRepository();

  def getNeighbourhood(c:Coordinate):Option[NeighbourhoodData] = {
    Option(repo.getNeighbourhood(c))
  }

  def getAll():List[String] = {
    for( i <- repo.getAll.asScala.toList) yield i.getName
  }

  def getByName(s:String):NeighbourhoodData = {
    repo.getOne(s)
  }
}
