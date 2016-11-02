package services.users.impl
import com.websudos.phantom.dsl.ResultSet
import domain.users.PersonDemographics
import services.users.PersonDemographicsService
import services.Service
import repositories.users.PersonDemographicsRepository

import scala.concurrent.Future
/**
  * Created by Lonwabo on 10/31/2016.
  */
class PersonDemographicsServiceImpl extends PersonDemographicsService with Service{
  override def createOrupdate(personDemographics: PersonDemographics): Future[ResultSet] = {
    PersonDemographicsRepository.save(personDemographics)
  }

  override def getPersonDemographicsById(organisationId: String, userId: String, personDemographicsid: String): Future[Option[PersonDemographics]] = {
    PersonDemographicsRepository.findById(organisationId,userId,personDemographicsid)
  }

  override def getPersonDemographics(person: String): Future[Seq[PersonDemographics]] = {
    PersonDemographicsRepository.findAll
  }
}
