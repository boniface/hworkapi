package services.users

import com.websudos.phantom.dsl._
import domain.users.UserIdentity
import services.users.impl.UserIdentityServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait UserIdentityService {
  def createOrupdate(personIdentity : UserIdentity):Future[ResultSet]

  def getPersonIdentityById(organisationId : String,userId: String, personIdentitytid: String): Future[Option[UserIdentity]]
  def getAllPersonIdentity(person: String):Future[Seq[UserIdentity]]
}

object UserIdentityService{
  def apply: UserIdentityService = new UserIdentityServiceImpl()
}
