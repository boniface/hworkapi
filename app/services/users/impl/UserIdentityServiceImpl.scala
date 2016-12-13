package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserIdentity
import services.Service
import services.users.UserIdentityService
import repositories.users.UserIdentityRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserIdentityServiceImpl extends UserIdentityService with Service{
  override def createOrupdate(personIdentity: UserIdentity): Future[ResultSet] = {
    UserIdentityRepository.save(personIdentity)
  }

  override def getPersonIdentityById(organisationId: String, userId: String, personIdentitytid: String): Future[Option[UserIdentity]] = {
    UserIdentityRepository.findById(organisationId,userId,personIdentitytid)
  }

  override def getAllPersonIdentity(person: String): Future[Seq[UserIdentity]] = {
    UserIdentityRepository.findAll
  }
}
