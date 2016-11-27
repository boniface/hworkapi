package services.users

import scala.concurrent.Future
import com.websudos.phantom.dsl.ResultSet
import domain.users.UserContinuingEducation
import services.users.impl.UserContinuingEducationServiceImpl

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserContinuingEducationService {

  def createOrupdate(userAttachment: UserContinuingEducation): Future[ResultSet]

  def getPersonContinuingEducationById(organisationId: String, userId: String, personContinuingEducationId: String): Future[Option[UserContinuingEducation]]

  def getPersonAttachments(personId: String): Future[Seq[UserContinuingEducation]]
}

object UserContinuingEducationService {
  def apply: UserContinuingEducationService = new UserContinuingEducationServiceImpl()
}
