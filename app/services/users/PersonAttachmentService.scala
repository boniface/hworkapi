package services.users

import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonAttachment
import services.users.impl.PersonAttachmentServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonAttachmentService {
  def createOrupdate(userAttachment : PersonAttachment):Future[ResultSet]

def getPersonAttachmentById(organisationId : String,userId: String, personAttachmentid: String): Future[Option[PersonAttachment]]
def getPersonAttachments(person: String):Future[Seq[PersonAttachment]]
}

object PersonAttachmentService{
  def apply: PersonAttachmentService = new PersonAttachmentServiceImpl()
}