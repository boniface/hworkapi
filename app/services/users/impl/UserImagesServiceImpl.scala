package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserImages
import services.users.UserImagesService
import services.Service
import repositories.users.UserImagesRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserImagesServiceImpl extends UserImagesService with Service{
  override def createOrupdate(personImages: UserImages): Future[ResultSet] = {
    UserImagesRepository.save(personImages)
  }

  override def getPersonImagesById(organisationId: String, userId: String, personImagesid: String): Future[Option[UserImages]] = {
    UserImagesRepository.findById(organisationId,userId,personImagesid)
  }

  override def getAllPersonImages(person: String): Future[Seq[UserImages]] = {
    UserImagesRepository.findAll
  }
}
