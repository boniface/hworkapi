package services.users

import com.websudos.phantom.dsl._
import domain.users.UserContinuingEducation
import services.users.impl.UserContinuingEducationServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserContinuingEducationService {
  def createOrupdate(userAttachment : UserContinuingEducation):Future[ResultSet]

  def getPersonContinuingEducationById(organisationId : String,userId: String, personContinuingEducationId : String): Future[Option[UserContinuingEducation]]
  def getPersonAttachments(person: String):Future[Seq[UserContinuingEducationService]]
}
object UserContinuingEducationService{
  def apply: UserContinuingEducationService = new UserContinuingEducationServiceImpl()
}
