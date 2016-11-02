package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonAttachment
import services.users.PersonAttachmentService
import services.Service
import scala.concurrent.Future
import repositories.users.PersonAttachmentRepository

/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonAttachmentServiceImpl extends PersonAttachmentService with Service {
  override def createOrupdate(personAttachment: PersonAttachment): Future[ResultSet] = {
    PersonAttachmentRepository.save(personAttachment)
  }

  override def getPersonAttachmentById(organisationId : String,userId: String, personAttachmentid: String): Future[Option[PersonAttachment]] = {
    PersonAttachmentRepository.findById(organisationId,userId,personAttachmentid)
  }

  override def getPersonAttachments(person: String): Future[Seq[PersonAttachment]] = {
    PersonAttachmentRepository.findAll
  }
}
