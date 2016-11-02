package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonEducationHistory
import services.users.impl.{PersonAttachmentServiceImpl, PersonEducationHistoryServiceImpl}

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonEducationHistoryService {
  def createOrupdate(personEducationHistory : PersonEducationHistory):Future[ResultSet]

  def getPersonEducationHistoryById(organisationId : String,userId: String, personEducationHistorytid: String): Future[Option[PersonEducationHistory]]
  def getPersonEducationHistories(person: String):Future[Seq[PersonEducationHistory]]
}

object PersonEducationHistoryService{
  def apply: PersonEducationHistoryService = new PersonEducationHistoryServiceImpl()
}