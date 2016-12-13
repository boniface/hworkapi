package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserEmploymentHistory
import repositories.users.UserEmploymentHistoryRepository
import services.Service
import services.users.UserEmploymentHistoryService

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserEmploymentHisttoryServiceImpl extends UserEmploymentHistoryService with Service{
  override def createOrupdate(personEmploymentHistory: UserEmploymentHistory): Future[ResultSet] = {
    UserEmploymentHistoryRepository.save(personEmploymentHistory)
  }
  override def getPersonEmploymentById(organisationId: String, userId: String, personEmploymentHistoryid: String): Future[Option[UserEmploymentHistory]] = {
    UserEmploymentHistoryRepository.findById(organisationId,userId,personEmploymentHistoryid)
  }

  override def getAllPersonEmploymentHistory(person: String): Future[Seq[UserEmploymentHistory]] = {
    UserEmploymentHistoryRepository.findAll
  }
}
