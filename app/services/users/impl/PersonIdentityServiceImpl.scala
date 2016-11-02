package services.users.impl
import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonIdentity
import services.Service
import services.users.PersonIdentityService
import repositories.users.PersonIdentityRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonIdentityServiceImpl extends PersonIdentityService with Service{
  override def createOrupdate(personIdentity: PersonIdentity): Future[ResultSet] = {
    PersonIdentityRepository.save(personIdentity)
  }

  override def getPersonIdentityById(organisationId: String, userId: String, personIdentitytid: String): Future[Option[PersonIdentity]] = {
    PersonIdentityRepository.findById(organisationId,userId,personIdentitytid)
  }

  override def getAllPersonIdentity(person: String): Future[Seq[PersonIdentity]] = {
    PersonIdentityRepository.findAll
  }
}
