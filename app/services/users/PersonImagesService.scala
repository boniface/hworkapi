package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonImages
import services.users.impl.PersonImagesServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonImagesService {
  def createOrupdate(personImages : PersonImages):Future[ResultSet]

  def getPersonImagesById(organisationId : String,userId: String, personImagesid: String): Future[Option[PersonImages]]
  def getAllPersonImages(person: String):Future[Seq[PersonImages]]
}

object PersonImagesService{
  def apply: PersonImagesService = new PersonImagesServiceImpl()
}