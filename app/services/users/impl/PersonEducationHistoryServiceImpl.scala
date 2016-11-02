package services.users.impl
import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonEducationHistory
import services.users.PersonEducationHistoryService
import services.Service
import repositories.users.PersonEducationHistoryRepository
import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonEducationHistoryServiceImpl extends PersonEducationHistoryService with Service{
  override def createOrupdate(personEducationHistory: PersonEducationHistory): Future[ResultSet] = {
    PersonEducationHistoryRepository.save(personEducationHistory)
  }

  override def getPersonEducationHistoryById(organisationId: String, userId: String, personEducationHistorytid: String): Future[Option[PersonEducationHistory]] = {
    PersonEducationHistoryRepository.findById(organisationId,userId,personEducationHistorytid)
  }

  override def getPersonEducationHistories(person: String): Future[Seq[PersonEducationHistory]] = {
    PersonEducationHistoryRepository.findAll
  }
}
