package services.users

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserAttachment
import services.users.impl.UserAttachmentServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserAttachmentService {
  def createOrupdate(userAttachment : UserAttachment):Future[ResultSet]

def getPersonAttachmentById(organisationId : String,userId: String, personAttachmentid: String): Future[Option[UserAttachment]]
def getPersonAttachments(person: String):Future[Seq[UserAttachment]]
}

object UserAttachmentService{
  def apply: UserAttachmentService = new UserAttachmentServiceImpl()
}
