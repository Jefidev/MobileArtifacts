package BusinessObjects

/**
  * Created by jfink on 03/04/18.
  */
object Orchestrators {

  def verify(token:String):Boolean = {
    if(token == "269544d3-99e7-4c52-b1c4-660f4a59df32")
      true
    else
      false
  }
}
