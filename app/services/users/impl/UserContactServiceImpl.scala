package services.users.impl

import com.websudos.phantom.dsl._
import domain.users.UserContact
import repositories.users.UserContactRepository
import services.Service
import services.users.UserContactService

import scala.concurrent.Future

/**
  * Created by SONY on 2016-10-24.
  */
class UserContactServiceImpl  extends UserContactService with Service {
  def createOrUpdate(personRole: UserContact): Future[ResultSet] = {
    UserContactRepository.save(personRole)
  }

  def getUserContactById(organisationId: String, userId: String, userContactId: String): Future[Option[UserContact]] = {
    UserContactRepository.findById(organisationId, userId, userContactId)
  }

  def getUserContact(organisationId: String): Future[Seq[UserContact]] = {
    UserContactRepository.getUserContact(organisationId)
  }
}

