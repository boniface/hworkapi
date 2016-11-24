package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonImages
import services.users.PersonImagesService
import services.Service
import repositories.users.PersonImagesRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonImagesServiceImpl extends PersonImagesService with Service{
  override def createOrupdate(personImages: PersonImages): Future[ResultSet] = {
    PersonImagesRepository.save(personImages)
  }

  override def getPersonImagesById(organisationId: String, userId: String, personImagesid: String): Future[Option[PersonImages]] = {
    PersonImagesRepository.findById(organisationId,userId,personImagesid)
  }

  override def getAllPersonImages(person: String): Future[Seq[PersonImages]] = {
    PersonImagesRepository.findAll
  }
}
