package BusinessObjects

import repositories.SensorsRepository

/**
  * Created by jfink on 03/04/18.
  */
object Orchestrators {

  val repo = new SensorsRepository()

  def verify(token:String):Boolean = {
    println("Connexion raspberry reçue")
    println(token)
    if(token == "269544d3-99e7-4c52-b1c4-660f4a59df32")
      true
    else
      false
  }


  def setValue(token:String, id:Int, value:Float) = {
    repo.setValue(token, id, value)
  }
}
