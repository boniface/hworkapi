package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonEmploymentHistory
import repositories.users.PersonEmploymentHistoryRepository
import services.Service

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonEmploymentHisttoryServiceImpl extends PersonEmploymentHistoryService with Service{
  override def createOrupdate(personEmploymentHistory: PersonEmploymentHistory): Future[ResultSet] = {
    PersonEmploymentHistoryRepository.save(personEmploymentHistory)
  }
  override def getPersonEmploymentById(organisationId: String, userId: String, personEmploymentHistoryid: String): Future[Option[PersonEmploymentHistory]] = {
    PersonEmploymentHistoryRepository.findById(organisationId,userId,personEmploymentHistoryid)
  }

  override def getAllPersonEmploymentHistory(person: String): Future[Seq[PersonEmploymentHistory]] = {
    PersonEmploymentHistoryRepository.findAll
  }
}
