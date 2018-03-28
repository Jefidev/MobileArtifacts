package BusinessObjects

import java.util.Collections

import scala.util.{Success, Try}
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory


/**
  * Created by jfink on 20/03/18.
  */

object User {

  val verifier:GoogleIdTokenVerifier =
    new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
      .setAudience(Collections.singletonList("435471669940-i0d6ksrnkc87mmbvpqvh121uspp9qur1.apps.googleusercontent.com"))
      .build()


  def verify(token:String):Boolean = {
    println(token)
    val idToken:Try[Option[GoogleIdToken]] = Try(Option(verifier.verify(token)))

    idToken match {
      case Success(Some(_)) => true
      case _ => false
    }
  }
}