package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserContinuingEducation
import repositories.users.UserContinuingEducationRepository
import services.Service
import services.users.UserContinuingEducationService

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserContinuingEducationServiceImpl extends UserContinuingEducationService with Service{
  override def createOrupdate(personContinuingEducation: UserContinuingEducation): Future[ResultSet] = {
    UserContinuingEducationRepository.save(personContinuingEducation)
  }

  override def getPersonContinuingEducationById(organisationId: String, userId: String, personContinuingEducationId: String): Future[Option[UserContinuingEducation]] = {
    UserContinuingEducationRepository.findById(organisationId,userId,personContinuingEducationId)
  }

  override def getPersonAttachments(person: String): Future[Seq[UserContinuingEducation]] = {
    UserContinuingEducationRepository.findAll
  }
}
