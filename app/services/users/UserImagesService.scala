package services.users

import com.websudos.phantom.dsl._
import domain.users.UserImages
import services.users.impl.UserImagesServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserImagesService {
  def createOrupdate(personImages : UserImages):Future[ResultSet]

  def getPersonImagesById(organisationId : String,userId: String, personImagesid: String): Future[Option[UserImages]]
  def getAllPersonImages(person: String):Future[Seq[UserImages]]
}

object UserImagesService{
  def apply: UserImagesService = new UserImagesServiceImpl()
}
