package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserPosition
import services.Service
import services.users.UserPositionService
import repositories.users.UserPositionRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserPositionServiceImpl extends UserPositionService with Service {
  override def createOrupdate(personPosition: UserPosition): Future[ResultSet] = {
    UserPositionRepository.save(personPosition)
  }

  override def getPersonPositionById(organisationId: String, userId: String, personPositionid: String): Future[Option[UserPosition]] = {
    UserPositionRepository.findById(organisationId,userId,personPositionid)
  }

  override def getAllPersonPositions(person: String): Future[Seq[UserPosition]] = {
    UserPositionRepository.findAll
  }
}
