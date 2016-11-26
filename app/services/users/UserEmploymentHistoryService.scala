package services.users

import com.websudos.phantom.dsl._
import domain.users.UserEmploymentHistory
import services.users.impl.UserEmploymentHisttoryServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserEmploymentHistoryService {
  def createOrupdate(personEmploymentHistory : UserEmploymentHistory):Future[ResultSet]

  def getPersonEmploymentById(organisationId : String,userId: String, personEmploymentHistoryid: String): Future[Option[UserEmploymentHistory]]
  def getAllPersonEmploymentHistory(person: String):Future[Seq[UserEmploymentHistory]]
}

object UserEmploymentHistoryService{
  def apply: UserEmploymentHistoryService = new UserEmploymentHisttoryServiceImpl()
}
