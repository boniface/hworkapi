package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserEducationHistory
import repositories.users.UserEducationHistoryRepository
import services.Service
import services.users.UserEducationHistoryService

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserEducationHistoryServiceImpl extends UserEducationHistoryService with Service{
  override def createOrupdate(personEducationHistory: UserEducationHistory): Future[ResultSet] = {
    UserEducationHistoryRepository.save(personEducationHistory)
  }

  override def getPersonEducationHistoryById(organisationId: String, userId: String, personEducationHistorytid: String): Future[Option[UserEducationHistory]] = {
    UserEducationHistoryRepository.findById(organisationId,userId,personEducationHistorytid)
  }

  override def getPersonEducationHistories(person: String): Future[Seq[UserEducationHistory]] = {
    UserEducationHistoryRepository.findAll
  }
}
