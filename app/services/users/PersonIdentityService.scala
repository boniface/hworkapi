package services.users

import com.websudos.phantom.dsl._
import domain.users.PersonIdentity
import services.users.impl.PersonIdentityServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonIdentityService {
  def createOrupdate(personIdentity : PersonIdentity):Future[ResultSet]

  def getPersonIdentityById(organisationId : String,userId: String, personIdentitytid: String): Future[Option[PersonIdentity]]
  def getAllPersonIdentity(person: String):Future[Seq[PersonIdentity]]
}

object PersonIdentityService{
  def apply: PersonIdentityService = new PersonIdentityServiceImpl()
}