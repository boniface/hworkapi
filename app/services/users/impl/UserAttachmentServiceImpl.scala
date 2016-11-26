package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserAttachment
import repositories.users.UserAttachmentRepository
import services.Service
import services.users.UserAttachmentService

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserAttachmentServiceImpl extends UserAttachmentService with Service {
  override def createOrupdate(personAttachment: UserAttachment): Future[ResultSet] = {
    UserAttachmentRepository.save(personAttachment)
  }

  override def getPersonAttachmentById(organisationId : String,userId: String, personAttachmentid: String): Future[Option[UserAttachment]] = {
    UserAttachmentRepository.findById(organisationId,userId,personAttachmentid)
  }

  override def getPersonAttachments(person: String): Future[Seq[UserAttachment]] = {
    UserAttachmentRepository.findAll
  }
}
