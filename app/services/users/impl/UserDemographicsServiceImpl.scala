package services.users.impl

import com.websudos.phantom.dsl.ResultSet
import domain.users.UserDemographics
import repositories.users.UserDemographicsRepository
import services.Service
import services.users.UserDemographicsService

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class UserDemographicsServiceImpl extends UserDemographicsService with Service{
  override def createOrupdate(personDemographics: UserDemographics): Future[ResultSet] = {
    UserDemographicsRepository.save(personDemographics)
  }

  override def getPersonDemographicsById(organisationId: String, userId: String, personDemographicsid: String): Future[Option[UserDemographics]] = {
    UserDemographicsRepository.findById(organisationId,userId,personDemographicsid)
  }

  override def getPersonDemographics(person: String): Future[Seq[UserDemographics]] = {
    UserDemographicsRepository.findAll
  }
}
