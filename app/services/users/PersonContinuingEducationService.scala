package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonContinuingEducation
import services.users.impl.PersonContinuingEducationServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonContinuingEducationService {
  def createOrupdate(userAttachment : PersonContinuingEducation):Future[ResultSet]

  def getPersonContinuingEducationById(organisationId : String,userId: String, personContinuingEducationId : String): Future[Option[PersonContinuingEducation]]
  def getPersonAttachments(person: String):Future[Seq[PersonContinuingEducationService]]
}
object PersonContinuingEducationService{
  def apply: PersonContinuingEducationService = new PersonContinuingEducationServiceImpl()
}