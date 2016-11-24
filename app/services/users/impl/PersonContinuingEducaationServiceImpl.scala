package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonContinuingEducation
import repositories.users.PersonContinuingEducationRepository
import services.Service

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonContinuingEducationServiceImpl extends PersonContinuingEducationService with Service{
  override def createOrupdate(personContinuingEducation: PersonContinuingEducation): Future[ResultSet] = {
    PersonContinuingEducationRepository.save(personContinuingEducation)
  }

  override def getPersonContinuingEducationById(organisationId: String, userId: String, personContinuingEducationId: String): Future[Option[PersonContinuingEducation]] = {
    PersonContinuingEducationRepository.findById(organisationId,userId,personContinuingEducationId)
  }

  override def getPersonAttachments(person: String): Future[Seq[PersonContinuingEducation]] = {
    PersonContinuingEducationRepository.findAll
  }
}
