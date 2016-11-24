package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonPosition
import services.Service
import services.users.PersonPositionService
import repositories.users.PersonPositionRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonPositionServiceImpl extends PersonPositionService with Service {
  override def createOrupdate(personPosition: PersonPosition): Future[ResultSet] = {
    PersonPositionRepository.save(personPosition)
  }

  override def getPersonPositionById(organisationId: String, userId: String, personPositionid: String): Future[Option[PersonPosition]] = {
    PersonPositionRepository.findById(organisationId,userId,personPositionid)
  }

  override def getAllPersonPositions(person: String): Future[Seq[PersonPosition]] = {
    PersonPositionRepository.findAll
  }
}
