package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonEmploymentHistory
import services.users.impl.PersonEmploymentHisttoryServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonEmploymentHistoryService {
  def createOrupdate(personEmploymentHistory : PersonEmploymentHistory):Future[ResultSet]

  def getPersonEmploymentById(organisationId : String,userId: String, personEmploymentHistoryid: String): Future[Option[PersonEmploymentHistory]]
  def getAllPersonEmploymentHistory(person: String):Future[Seq[PersonEmploymentHistory]]
}

object PersonEmploymentHistoryService{
  def apply: PersonEmploymentHistoryService = new PersonEmploymentHisttoryServiceImpl()
}