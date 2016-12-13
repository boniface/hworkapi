package services.users

import com.websudos.phantom.dsl._
import domain.users.UserEducationHistory
import services.users.impl.UserEducationHistoryServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserEducationHistoryService {
  def createOrupdate(personEducationHistory : UserEducationHistory):Future[ResultSet]

  def getPersonEducationHistoryById(organisationId : String,userId: String, personEducationHistorytid: String): Future[Option[UserEducationHistory]]
  def getPersonEducationHistories(person: String):Future[Seq[UserEducationHistory]]
}

object UserEducationHistoryService{
  def apply: UserEducationHistoryService = new UserEducationHistoryServiceImpl()
}
