package services.users

import com.websudos.phantom.dsl._
import domain.users.{PersonContinuingEducation, PersonDemographics}
import services.users.impl.PersonDemographicsServiceImpl

import scala.concurrent.Future

/**
  * Created by Lonwabo on 10/31/2016.
  */
trait PersonDemographicsService {
  def createOrupdate(personDemographics : PersonDemographics):Future[ResultSet]

  def getPersonDemographicsById(organisationId : String,userId: String, personDemographicsid : String): Future[Option[PersonDemographics]]
  def getPersonDemographics(person: String):Future[Seq[PersonDemographics]]
}
object PersonDemographicsService{
  def apply: PersonDemographicsServiceImpl = new PersonDemographicsServiceImpl()
}