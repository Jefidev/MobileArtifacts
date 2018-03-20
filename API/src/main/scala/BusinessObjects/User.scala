package BusinessObjects

import java.util.UUID

import scala.collection.mutable

/**
  * Created by jfink on 20/03/18.
  */
case class User(id: UUID, name:String, pwd:String, token:Int)

object User {

  val bd: mutable.Map[UUID, User] = mutable.Map.empty[UUID, User]

  def isConnected(t:Int): Boolean ={
    bd.exists(x => x._2.token == t)
  }

  def login(token:String): Unit ={
    //vérifier le token de google
  }
}