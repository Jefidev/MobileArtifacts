package BusinessObjects

import querydsl.NeighbourhoodData
import repositories.NeighbourhoodRepository

/**
  * Created by jfink on 03/04/18.
  */
object Neighbourhoods {

  val repo = new NeighbourhoodRepository();

  def getNeighbourhood(c:Coordinate):Option[NeighbourhoodData] = {
    Option(repo.getNeighbourhood(c))
  }
  
}
