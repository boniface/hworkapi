package services.users

import com.websudos.phantom.dsl._
import domain.users.UserContact
import services.users.impl.UserContactServiceImpl

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-24.
  */
trait UserContactService
{
  def createOrUpdate(personRole: UserContact): Future[ResultSet]

  def getUserContactById(organisationId: String, userId: String, userContactId: String): Future[Option[UserContact]]

  def getUserContact(organisationId: String): Future[Seq[UserContact]]
}

object UserContactService{
  def apply: UserContactService = new UserContactServiceImpl()
}
