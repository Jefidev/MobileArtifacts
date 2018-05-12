package BusinessObjects

import repositories.SensorsRepository

/**
  * Created by jfink on 03/04/18.
  */
object Orchestrators {

  val repo = new SensorsRepository()

  /**
    * Check Raspberry Pi token
    * @param token Raspberry Pi token
    * @return true if the token is correct false otherwise
    */
  def verify(token:String):Boolean = {
    println("Connexion raspberry reçue")
    println(token)
    token == "269544d3-99e7-4c52-b1c4-660f4a59df32"
  }

  /**
    * Sets the token to be used for database queries
    * @param token Raspberry Pi token
    * @param id Sensor ID
    * @param value Sensor value
    */
  def setValue(token:String, id:Int, value:Float) = {
    repo.setValue(token, id, value)
  }
}
